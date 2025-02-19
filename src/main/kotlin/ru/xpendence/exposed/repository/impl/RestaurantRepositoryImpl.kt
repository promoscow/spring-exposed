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
import ru.xpendence.exposed.repository.entity.DishEntity
import ru.xpendence.exposed.repository.entity.OrderEntity
import ru.xpendence.exposed.repository.entity.RestaurantEntity
import ru.xpendence.exposed.repository.mapper.toRestaurant
import java.util.UUID

@Repository
class RestaurantRepositoryImpl: RestaurantRepository {

    override fun getAllByUserOrdered(userId: UUID): List<Restaurant> = transaction {
        RestaurantEntity
            .rightJoin(DishEntity)
            .join(
                otherTable = OrderEntity,
                joinType = JoinType.RIGHT,
                onColumn = DishEntity.id,
                otherColumn = OrderEntity.dishId
            )
            .select(RestaurantEntity.columns)
            .where { OrderEntity.userId eq userId }
            .map { it.toRestaurant() }
    }

    override fun getByNameILike(namePart: String): List<Restaurant> = transaction {
        RestaurantEntity
            .selectAll()
            .where { RestaurantEntity.name iLike "%$namePart%" }
            .map { it.toRestaurant() }
    }
}

class ILikeOp(expr1: Expression<*>, expr2: Expression<*>) : ComparisonOp(expr1, expr2, "ILIKE")

infix fun ExpressionWithColumnType<String>.iLike(pattern: String): Op<Boolean> =
    ILikeOp(this, QueryParameter(pattern, columnType))