package ru.xpendence.exposed.repository

import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.exposed.ExposedApplicationTests
import ru.xpendence.exposed.domain.Restaurant
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RestaurantRepositoryTest : ExposedApplicationTests() {

    @Autowired
    private lateinit var repository: RestaurantRepository

    @Test
    @DisplayName("insert(): успешное сохранение")
    fun insert() {
        //given
        val restaurant = Restaurant(
            name = RandomStringUtils.secure().nextAlphanumeric(32)
        )
        //when
        val saved = repository.insert(restaurant)
            .also {
                //then
                assertNotNull(it.id)
                assertEquals(restaurant.name, it.name)
            }
        //after
        repository.delete(saved.id!!)
    }

    @Test
    @DisplayName("update(): успешное обновление поля name")
    fun update() {
        //given
        val restaurant = Restaurant(
            name = RandomStringUtils.secure().nextAlphanumeric(32)
        )
        val saved = repository.insert(restaurant)
        //when
        val newName = RandomStringUtils.secure().nextAlphanumeric(32)
        saved.copy(name = newName).also { repository.update(it) }
        //then
        repository.findById(saved.id!!)
            .also { assertEquals(newName, it!!.name) }
        //after
        repository.delete(saved.id!!)
    }

    @Test
    @DisplayName("findById(): сохранённый ресторан успешно найден")
    fun findById() {
        //given
        val restaurant = Restaurant(
            name = RandomStringUtils.secure().nextAlphanumeric(32)
        )
        val saved = repository.insert(restaurant)
        //when
        repository.findById(saved.id!!)
            .also {
                //then
                assertNotNull(it)
            }
        //after
        repository.delete(saved.id!!)
    }

    @Test
    @DisplayName("getRestaurants(): проблема N + 1. Проверяем, был ли пользователь в ресторане")
    fun getRestaurants() {
        val userId = UUID.fromString("1031f963-957c-425f-962e-767080a699cb")
        val restaurants = repository.getAllByUserOrdered(userId)

        assertTrue { restaurants.map { it.name }.contains("Три поросёнка") }
    }

    @Test
    @DisplayName("getByNameILike(): позитивный")
    fun getByNameILike() {
        val namePart = "большая"
        val restaurants = repository.getByNameILike(namePart)
        assertTrue { restaurants.isNotEmpty() }
    }

    @Test
    @DisplayName("delete(): успешно удаляет ресторан")
    fun delete() {
        //given
        val restaurant = Restaurant(
            name = RandomStringUtils.secure().nextAlphanumeric(32)
        )
        val saved = repository.insert(restaurant)
        //when
        repository.delete(saved.id!!)
        //then
        repository.findById(saved.id!!).also { assertNull(it) }
    }
}