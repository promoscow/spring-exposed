package ru.xpendence.exposed.repository

interface DishRepository {

    fun countOrderedByName(name: String): Long
}