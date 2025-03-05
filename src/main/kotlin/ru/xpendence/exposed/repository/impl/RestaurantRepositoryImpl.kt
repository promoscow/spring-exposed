package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import ru.xpendence.exposed.domain.Restaurant
import ru.xpendence.exposed.repository.RestaurantRepository
import ru.xpendence.exposed.repository.mapper.toRestaurant
import ru.xpendence.exposed.repository.table.DishTable
import ru.xpendence.exposed.repository.table.OrderTable
import ru.xpendence.exposed.repository.table.RestaurantTable
import ru.xpendence.exposed.repository.utils.iLike
import ru.xpendence.exposed.repository.utils.toJson
import java.util.UUID

@Repository
class RestaurantRepositoryImpl : RestaurantRepository {

    override fun insert(restaurant: Restaurant): Restaurant = transaction {
        RestaurantTable
            .insert {
                it[id] = UUID.randomUUID()
                it[name] = restaurant.name
            }
            .resultedValues
            ?.single()
            ?.toRestaurant()
            ?: throw IllegalStateException("Error inserting restaurant: $${restaurant.toJson()}. Result is null.")
    }

    override fun update(restaurant: Restaurant) {
        transaction {
            RestaurantTable
                .update({ RestaurantTable.id eq restaurant.id!! }) {
                    it[name] = restaurant.name
                }
        }
    }

    override fun findById(id: UUID): Restaurant? = transaction {
        RestaurantTable
            .selectAll()
            .where { RestaurantTable.id eq id }
            .singleOrNull()
            ?.toRestaurant()
    }

    override fun getAllByUserOrdered(userId: UUID): List<Restaurant> = transaction {
        RestaurantTable
            .rightJoin(DishTable)
            .join(
                otherTable = OrderTable,
                joinType = JoinType.RIGHT,
                onColumn = DishTable.id,
                otherColumn = OrderTable.dishId
            )
            .select(RestaurantTable.columns)
            .where { OrderTable.userId eq userId }
            .map { it.toRestaurant() }
    }

    override fun getByNameILike(namePart: String): List<Restaurant> = transaction {
        RestaurantTable
            .selectAll()
            .where { RestaurantTable.name iLike "%$namePart%" }
            .map { it.toRestaurant() }
    }

    override fun delete(id: UUID) {
        transaction {
            RestaurantTable
                .deleteWhere { RestaurantTable.id eq id }
        }
    }
}