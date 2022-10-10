package com.sarahisweird.sarahsfurniture.util.arrp.recipe

import net.devtech.arrp.json.recipe.JIngredient
import net.minecraft.item.Item

class JIngredientContext {
    val ingredient = JIngredient.ingredient()

    fun item(item: Item) {
        ingredient.item(item)
    }

    fun item(id: String) {
        ingredient.item(id)
    }

    fun tag(tag: String) {
        ingredient.tag(tag)
    }

    fun addIngredient(otherIngredient: JIngredient) {
        ingredient.add(otherIngredient)
    }
}

fun arrpIngredient(builder: JIngredientContext.() -> Unit): JIngredient {
    val context = JIngredientContext()

    builder.invoke(context)

    return context.ingredient
}