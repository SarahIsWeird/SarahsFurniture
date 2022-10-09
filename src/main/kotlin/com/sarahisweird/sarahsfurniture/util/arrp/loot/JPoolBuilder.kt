package com.sarahisweird.sarahsfurniture.util.arrp.loot

import net.devtech.arrp.json.loot.*

class JPoolContext {
    val pool = JPool()

    fun entry(entry: JEntry) {
        pool.entry(entry)
    }

    fun entry(builder: JEntryContext.() -> Unit) =
        entry(arrpEntry(builder))

    fun condition(condition: JCondition) {
        pool.condition(condition)
    }

    fun condition(builder: JConditionContext.() -> Unit) =
        condition(arrpCondition(builder))

    fun condition(condition: String) =
        this.condition(arrpCondition(condition))

    fun function(function: JFunction) {
        pool.function(function)
    }

    fun function(function: String, builder: JFunctionContext.() -> Unit = {}) =
        function(arrpFunction(function, builder))

    fun rolls(rolls: Int) {
        pool.rolls(rolls)
    }

    fun rolls(min: Int, max: Int) {
        pool.rolls(JRoll(min, max))
    }

    fun bonus(bonusRolls: Int) {
        pool.bonus(bonusRolls)
    }

    fun bonus(min: Int, max: Int) {
        pool.bonus(JRoll(min, max))
    }
}

fun arrpPool(builder: JPoolContext.() -> Unit): JPool {
    val context = JPoolContext()

    builder.invoke(context)

    return context.pool
}