package com.sarahisweird.sarahsfurniture.util.arrp.blockstate

import net.devtech.arrp.json.blockstate.JBlockModel
import net.devtech.arrp.json.blockstate.JVariant
import net.minecraft.util.Identifier
import net.minecraft.util.StringIdentifiable

class JVariantContext {
    val variant = JVariant()

    fun put(key: String, model: JBlockModel) {
        variant.put(key, model)
    }

    fun put(key: String, identifier: Identifier, builder: JBlockModelContext.() -> Unit) =
        put(key, arrpBlockModel(identifier, builder))

    fun put(property: String, value: Boolean, model: JBlockModel) {
        variant.put(property, value, model)
    }

    fun put(property: String, value: Boolean, identifier: Identifier, builder: JBlockModelContext.() -> Unit) =
        put(property, value, arrpBlockModel(identifier, builder))

    fun put(property: String, value: Int, model: JBlockModel) {
        variant.put(property, value, model)
    }

    fun put(property: String, value: Int, identifier: Identifier, builder: JBlockModelContext.() -> Unit) =
        put(property, value, arrpBlockModel(identifier, builder))

    fun put(property: String, value: StringIdentifiable, model: JBlockModel) {
        variant.put(property, value, model)
    }

    fun put(property: String, value: StringIdentifiable, identifier: Identifier, builder: JBlockModelContext.() -> Unit) =
        put(property, value, arrpBlockModel(identifier, builder))

    fun put(property: String, value: String, model: JBlockModel) {
        variant.put(property, value, model)
    }

    fun put(property: String, value: String, identifier: Identifier, builder: JBlockModelContext.() -> Unit) =
        put(property, value, arrpBlockModel(identifier, builder))
}

fun arrpVariant(builder: JVariantContext.() -> Unit): JVariant {
    val context = JVariantContext()

    builder.invoke(context)

    return context.variant
}