package ru.xpendence.exposed.domain

import java.math.BigDecimal
import java.util.UUID

class Dish(
    val id: UUID,
    var name: String,
    var price: BigDecimal,
    var active: Boolean,
    var version: Int,
    val restaurant: Restaurant,
    val orders: List<Order> = listOf()
)

data class DishForSave(
    var name: String,
    var price: BigDecimal,
    var active: Boolean,
    var version: Int,
    val restaurantId: UUID
)