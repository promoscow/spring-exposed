package ru.xpendence.exposed.repository.table

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.javatime.date
import java.util.UUID

object OrderTable : IdTable<UUID>("orders") {
    override val id = uuid("id").entityId()
    override val primaryKey = PrimaryKey(id)
    val date = date("date")
    val userId = reference("user_id", UserTable)
    val dishId = reference("dish_id", RestaurantTable)
}