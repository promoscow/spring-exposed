package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import ru.xpendence.exposed.repository.DishRepository
import ru.xpendence.exposed.repository.entity.DishEntity
import ru.xpendence.exposed.repository.entity.OrderEntity
import ru.xpendence.exposed.repository.entity.RestaurantEntity
import ru.xpendence.exposed.repository.entity.UserEntity

@Repository
class DishRepositoryImpl : DishRepository {

    override fun countOrderedByName(name: String): Long =
        transaction {
            DishEntity
                .innerJoin(RestaurantEntity)
                .innerJoin(OrderEntity)
                .innerJoin(UserEntity)
                .selectAll()
                .where { DishEntity.name eq name }
                .count()
        }
}