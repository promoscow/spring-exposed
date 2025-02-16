package ru.xpendence.exposed.repository.entity

import org.jetbrains.exposed.dao.id.IdTable
import java.util.UUID

object RestaurantEntity : IdTable<UUID>("restaurants") {
    override val id = uuid("id").entityId()
    override val primaryKey = PrimaryKey(id)
    val name = varchar("name", 255)
}