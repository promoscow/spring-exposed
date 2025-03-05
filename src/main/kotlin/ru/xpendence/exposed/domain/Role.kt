package ru.xpendence.exposed.domain

import ru.xpendence.exposed.domain.type.RoleType
import java.util.UUID

class Role(
    val id: UUID? = null,
    val name: RoleType? = null
)