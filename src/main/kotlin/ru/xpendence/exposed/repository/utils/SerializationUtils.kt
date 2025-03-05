package ru.xpendence.exposed.repository.utils

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import org.openapitools.jackson.nullable.JsonNullableModule

val objectMapperKt: JsonMapper = jacksonMapperBuilder()
    .addModule(JavaTimeModule())
    .addModule(JsonNullableModule())
    .build()

fun <T> T.toJson(): String = objectMapperKt.writeValueAsString(this)