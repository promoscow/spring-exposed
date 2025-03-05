package ru.xpendence.exposed.repository

import ru.xpendence.exposed.domain.Restaurant
import java.util.UUID

/**
 * Репозиторий для работы с записями ресторанов.
 */
interface RestaurantRepository {

    /**
     * Сохранение записи о ресторане.
     *
     * @param restaurant - ресторан для сохранения
     * @return сохранённый ресторан
     */
    fun insert(restaurant: Restaurant): Restaurant

    /**
     * Обновление записи о ресторане.
     *
     * @param restaurant - ресторан для обновления
     */
    fun update(restaurant: Restaurant)

    /**
     * Поиск ресторана по идентификатору.
     *
     * @param id - идентификатор ресторана
     * @return найденный ресторан
     */
    fun findById(id: UUID): Restaurant?

    /**
     * Получение списка ресторанов, в которых пользователь делал заказ.
     *
     * @param userId - идентификатор пользователя
     * @return список найденных ресторанов
     */
    fun getAllByUserOrdered(userId: UUID): List<Restaurant>

    /**
     * Поиск ресторанов по частичному совпадению имени.
     *
     * @param namePart - часть имени
     * @return список найденных ресторанов
     */
    fun getByNameILike(namePart: String): List<Restaurant>

    /**
     * Удаление ресторана.
     *
     * @param id - идентификатор ресторана для удаления
     */
    fun delete(id: UUID)
}