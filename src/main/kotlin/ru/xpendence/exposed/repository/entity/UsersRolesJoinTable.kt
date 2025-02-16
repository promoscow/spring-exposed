package ru.xpendence.exposed.repository.entity

import org.jetbrains.exposed.sql.Table

object UsersRolesJoinTable : Table("users_roles") {
    val userId = reference("user_id", UserEntity)
    val roleId = reference("role_id", RoleEntity)
}