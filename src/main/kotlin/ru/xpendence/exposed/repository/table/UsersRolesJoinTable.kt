package ru.xpendence.exposed.repository.table

import org.jetbrains.exposed.sql.Table

object UsersRolesJoinTable : Table("users_roles") {
    val userId = reference("user_id", UserTable)
    val roleId = reference("role_id", RoleTable)
}