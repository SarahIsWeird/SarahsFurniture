package com.sarahisweird.sarahsfurniture.util.arrp.blockstate

import net.devtech.arrp.json.blockstate.JWhen
import net.minecraft.state.property.Property

class JWhenContext {
    val jWhen = JWhen()

    fun add(condition: String, vararg states: String) {
        jWhen.add(condition, *states)
    }

    fun <T : Comparable<T>> add(property: Property<T>, vararg values: T) {
        jWhen.add(property, *values)
    }
}

fun arrpWhen(builder: JWhenContext.() -> Unit): JWhen {
    val context = JWhenContext()

    builder.invoke(context)

    return context.jWhen
}