package ru.xpendence.exposed.repository.table

import org.jetbrains.exposed.dao.id.IdTable
import java.util.UUID

object UserTable : IdTable<UUID>("users") {
    override val id = uuid("id").entityId()
    override val primaryKey = PrimaryKey(id)
    val username = varchar("username", 255)
    val password = varchar("password", 255)
}