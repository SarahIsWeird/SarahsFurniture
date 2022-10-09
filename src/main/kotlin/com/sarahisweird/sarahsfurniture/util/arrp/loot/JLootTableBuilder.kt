package com.sarahisweird.sarahsfurniture.util.arrp.loot

import net.devtech.arrp.json.loot.JLootTable
import net.devtech.arrp.json.loot.JPool

class JLootTableContext(type: String) {
    val lootTable = JLootTable(type)

    fun pool(pool: JPool) {
        lootTable.pool(pool)
    }

    fun pool(builder: JPoolContext.() -> Unit) =
        pool(arrpPool(builder))
}

fun arrpLootTable(type: String, builder: JLootTableContext.() -> Unit): JLootTable {
    val context = JLootTableContext(type)

    builder.invoke(context)

    return context.lootTable
}