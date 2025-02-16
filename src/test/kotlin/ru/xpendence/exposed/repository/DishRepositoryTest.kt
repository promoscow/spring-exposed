package ru.xpendence.exposed.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.exposed.ExposedApplicationTests
import kotlin.test.assertEquals

class DishRepositoryTest : ExposedApplicationTests() {

    @Autowired
    private lateinit var repository: DishRepository

    @Test
    fun countOrdersByName() {
        repository.countOrderedByName("Харчо с изюминкой").also { assertEquals(2, it) }
    }
}