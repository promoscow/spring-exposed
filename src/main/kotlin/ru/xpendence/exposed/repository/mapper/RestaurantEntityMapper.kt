package ru.xpendence.exposed.repository.mapper

import org.jetbrains.exposed.sql.ResultRow
import ru.xpendence.exposed.domain.Restaurant
import ru.xpendence.exposed.repository.entity.RestaurantEntity

fun ResultRow.toRestaurant(): Restaurant = Restaurant(
    id = this[RestaurantEntity.id].value,
    name = this[RestaurantEntity.name]
)