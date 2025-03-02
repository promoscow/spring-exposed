package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.ComparisonOp
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.ExpressionWithColumnType
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.QueryParameter
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import ru.xpendence.exposed.domain.Restaurant
import ru.xpendence.exposed.repository.RestaurantRepository
import ru.xpendence.exposed.repository.mapper.toRestaurant
import ru.xpendence.exposed.repository.table.DishTable
import ru.xpendence.exposed.repository.table.OrderTable
import ru.xpendence.exposed.repository.table.RestaurantTable
import java.util.UUID

@Repository
class RestaurantRepositoryImpl: RestaurantRepository {

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
}

class ILikeOp(expr1: Expression<*>, expr2: Expression<*>) : ComparisonOp(expr1, expr2, "ILIKE")

infix fun ExpressionWithColumnType<String>.iLike(pattern: String): Op<Boolean> =
    ILikeOp(this, QueryParameter(pattern, columnType))