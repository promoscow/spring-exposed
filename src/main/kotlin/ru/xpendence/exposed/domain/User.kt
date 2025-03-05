package ru.xpendence.exposed.domain

import java.util.UUID

class User(
    val id: UUID? = null,
    val username: String? = null,
    var password: String? = null,
    val roles: Set<Role> = setOf()
)
