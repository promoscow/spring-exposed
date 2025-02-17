package ru.xpendence.exposed.repository

import ru.xpendence.exposed.domain.Restaurant
import java.util.UUID

interface RestaurantRepository {

    fun getAllByUserOrdered(userId: UUID): List<Restaurant>
}