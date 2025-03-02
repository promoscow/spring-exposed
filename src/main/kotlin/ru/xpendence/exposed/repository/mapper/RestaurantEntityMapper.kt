package ru.xpendence.exposed.repository.mapper

import org.jetbrains.exposed.sql.ResultRow
import ru.xpendence.exposed.domain.Restaurant
import ru.xpendence.exposed.repository.table.RestaurantTable

fun ResultRow.toRestaurant(): Restaurant = Restaurant(
    id = this[RestaurantTable.id].value,
    name = this[RestaurantTable.name]
)