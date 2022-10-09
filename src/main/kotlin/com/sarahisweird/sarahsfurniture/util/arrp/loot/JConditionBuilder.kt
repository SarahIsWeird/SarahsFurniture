package com.sarahisweird.sarahsfurniture.util.arrp.loot

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.devtech.arrp.json.loot.JCondition
import net.minecraft.util.Identifier

class JConditionContext {
    val condition = JCondition()

    fun set(parameters: JsonObject) {
        condition.set(parameters)
    }

    fun condition(condition: String) {
        this.condition.condition(condition)
    }

    fun parameter(key: String, value: JsonElement) {
        condition.parameter(key, value)
    }

    fun parameter(key: String, value: String) {
        condition.parameter(key, value)
    }

    fun parameter(key: String, value: Number) {
        condition.parameter(key, value)
    }

    fun parameter(key: String, value: Boolean) {
        condition.parameter(key, value)
    }

    fun parameter(key: String, value: Identifier) {
        condition.parameter(key, value)
    }

    fun parameter(key: String, value: Char) {
        condition.parameter(key, value)
    }
}

class AlternativesContext {
    private val _conditions = mutableListOf<JCondition>()
    val conditions: List<JCondition>
        get() = _conditions

    fun condition(builder: JConditionContext.() -> Unit) {
        _conditions += arrpCondition(builder)
    }
}

fun arrpCondition(builder: JConditionContext.() -> Unit): JCondition {
    val context = JConditionContext()

    builder.invoke(context)

    return context.condition
}

fun arrpCondition(condition: String) =
    JCondition(condition)

fun arrpAlternatives(builder: AlternativesContext.() -> Unit): JCondition {
    val context = AlternativesContext()

    builder.invoke(context)

    if (context.conditions.isEmpty()) throw IllegalStateException("No condition was given for alternatives.")

    val firstCondition = context.conditions.first()

    if (context.conditions.size == 1) return firstCondition

    context.conditions.drop(1).forEach(firstCondition::alternative)

    return firstCondition
}