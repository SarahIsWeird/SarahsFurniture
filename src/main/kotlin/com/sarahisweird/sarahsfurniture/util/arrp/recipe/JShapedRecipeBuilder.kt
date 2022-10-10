package com.sarahisweird.sarahsfurniture.util.arrp.recipe

import net.devtech.arrp.json.recipe.*
import net.minecraft.item.Item
import net.minecraft.util.Identifier

class JShapedRecipeContext {
    var pattern: JPattern? = null
        private set

    val keys = JKeys.keys()
    var result: JResult? = null
        private set

    fun pattern(row1: String, row2: String, row3: String) {
        pattern = JPattern.pattern(row1, row2, row3)
    }

    fun pattern(row1: String, row2: String) {
        pattern = JPattern.pattern(row1, row2)
    }

    fun pattern(row: String) {
        pattern = JPattern.pattern(row)
    }

    fun key(key: String, item: Item) {
        keys.key(key, JIngredient.ingredient().item(item))
    }

    fun key(key: String, item: String) {
        keys.key(key, JIngredient.ingredient().item(item))
    }

    fun key(key: String, item: Identifier) =
        key(key, item.toString())

    fun key(key: String, ingredient: JIngredient) {
        keys.key(key, ingredient)
    }

    fun key(key: String, builder: JIngredientContext.() -> Unit) {
        keys.key(key, arrpIngredient(builder))
    }

    fun result(item: Item) {
        result = JResult.item(item)
    }

    fun result(item: Item, count: Int) {
        result = JResult.itemStack(item, count)
    }

    fun result(id: String) {
        result = JResult.result(id)
    }

    fun result(id: String, count: Int) {
        result = JResult.stackedResult(id, count)
    }

    fun result(id: Identifier) =
        result(id.toString())

    fun result(id: Identifier, count: Int) =
        result(id.toString(), count)
}

fun arrpShapedRecipe(builder: JShapedRecipeContext.() -> Unit): JShapedRecipe {
    val context = JShapedRecipeContext()

    builder.invoke(context)

    if (context.pattern == null || context.result == null) throw IllegalStateException("No pattern or result given!")

    return JRecipe.shaped(context.pattern, context.keys, context.result)
}