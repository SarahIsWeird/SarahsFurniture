package com.sarahisweird.sarahsfurniture.util.arrp.loot

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.devtech.arrp.json.loot.JCondition
import net.devtech.arrp.json.loot.JFunction
import net.minecraft.util.Identifier

class JFunctionContext(function: String) {
    val function = JFunction(function)

    fun function(function: String) {
        this.function.function(function)
    }

    fun set(properties: JsonObject) {
        function.set(properties)
    }

    fun parameter(key: String, value: JsonElement) {
        function.parameter(key, value)
    }

    fun parameter(key: String, value: String) {
        function.parameter(key, value)
    }

    fun parameter(key: String, value: Number) {
        function.parameter(key, value)
    }

    fun parameter(key: String, value: Boolean) {
        function.parameter(key, value)
    }

    fun parameter(key: String, value: Identifier) {
        function.parameter(key, value)
    }

    fun parameter(key: String, value: Char) {
        function.parameter(key, value)
    }

    fun condition(condition: JCondition) {
        function.condition(condition)
    }

    fun condition(builder: JConditionContext.() -> Unit) =
        condition(arrpCondition(builder))

    fun condition(condition: String) =
        this.condition(arrpCondition(condition))

    fun conditionAlternatives(builder: AlternativesContext.() -> Unit) =
        condition(arrpAlternatives(builder))
}

fun arrpFunction(function: String, builder: JFunctionContext.() -> Unit): JFunction{
    val context = JFunctionContext(function)

    builder.invoke(context)

    return context.function
}