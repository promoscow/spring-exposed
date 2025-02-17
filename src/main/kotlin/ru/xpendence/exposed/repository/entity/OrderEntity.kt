package ru.xpendence.exposed.repository.entity

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.javatime.date
import java.util.UUID

object OrderEntity : IdTable<UUID>("orders") {
    override val id = uuid("id").entityId()
    override val primaryKey = PrimaryKey(id)
    val date = date("date")
    val userId = reference("user_id", UserEntity)
    val dishId = reference("dish_id", RestaurantEntity)
}