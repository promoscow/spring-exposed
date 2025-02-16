package ru.xpendence.exposed.repository.entity

import org.jetbrains.exposed.dao.id.IdTable
import java.util.UUID

object DishEntity : IdTable<UUID>("dishes") {
    override val id = uuid("id").entityId()
    override val primaryKey = PrimaryKey(id)
    val name = varchar("name", 255)
    val price = decimal("price", 19, 2)
    val active = bool("active")
    val restaurantId = reference("restaurant_id", RestaurantEntity)
}