package com.sarahisweird.sarahsfurniture.util.arrp.loot

import net.devtech.arrp.json.loot.JCondition
import net.devtech.arrp.json.loot.JEntry
import net.devtech.arrp.json.loot.JFunction

class JEntryContext {
    val entry = JEntry()

    fun type(type: String) {
        entry.type(type)
    }

    fun name(name: String) {
        entry.name(name)
    }

    fun child(child: JEntry) {
        entry.child(child)
    }

    fun child(builder: JEntryContext.() -> Unit) =
        child(arrpEntry(builder))

    fun expand(expand: Boolean = true) {
        entry.expand(expand)
    }

    fun function(function: JFunction) {
        entry.function(function)
    }

    fun function(function: String, builder: JFunctionContext.() -> Unit = {}) =
        entry.function(arrpFunction(function, builder))

    fun condition(condition: JCondition) {
        entry.condition(condition)
    }

    fun condition(condition: String) {
        entry.condition(condition)
    }

    fun conditionAlternatives(builder: AlternativesContext.() -> Unit) =
        condition(arrpAlternatives(builder))

    fun weight(weight: Int) {
        entry.weight(weight)
    }

    fun quality(quality: Int) {
        entry.quality(quality)
    }
}

fun arrpEntry(builder: JEntryContext.() -> Unit): JEntry {
    val context = JEntryContext()

    builder.invoke(context)

    return context.entry
}