package ru.xpendence.exposed.repository.utils

import org.jetbrains.exposed.sql.ComparisonOp
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.ExpressionWithColumnType
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.QueryParameter

class ILikeOp(expr1: Expression<*>, expr2: Expression<*>) : ComparisonOp(expr1, expr2, "ILIKE")

infix fun ExpressionWithColumnType<String>.iLike(pattern: String): Op<Boolean> =
    ILikeOp(this, QueryParameter(pattern, columnType))