package ru.xpendence.exposed.repository.table

import org.jetbrains.exposed.dao.id.IdTable
import java.util.UUID

object RoleTable : IdTable<UUID>("roles") {
    override val id = uuid("id").entityId()
    override val primaryKey = PrimaryKey(id)
    val name = varchar("name", 255)
}