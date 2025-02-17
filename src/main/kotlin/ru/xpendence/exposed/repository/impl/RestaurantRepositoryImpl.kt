package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.JoinType
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
}