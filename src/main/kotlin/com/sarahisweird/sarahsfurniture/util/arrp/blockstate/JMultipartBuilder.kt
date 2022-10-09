package com.sarahisweird.sarahsfurniture.util.arrp.blockstate

import net.devtech.arrp.json.blockstate.JBlockModel
import net.devtech.arrp.json.blockstate.JMultipart
import net.devtech.arrp.json.blockstate.JWhen
import net.minecraft.util.Identifier

class JMultipartContext {
    val multipart = JMultipart()

    fun whenCondition(condition: String, value: String) =
        whenCondition { add(condition, value) }

    fun whenCondition(jWhen: JWhen) {
        multipart.`when`(jWhen)
    }

    fun whenCondition(builder: JWhenContext.() -> Unit) =
        whenCondition(arrpWhen(builder))

    fun addModel(model: JBlockModel) {
        multipart.addModel(model)
    }

    fun addModel(identifier: Identifier) {
        multipart.addModel(JBlockModel(identifier))
    }

    fun addModel(identifier: Identifier, builder: JBlockModelContext.() -> Unit) {
        multipart.addModel(arrpBlockModel(identifier, builder))
    }
}

fun arrpMultipart(builder: JMultipartContext.() -> Unit): JMultipart {
    val context = JMultipartContext()

    builder.invoke(context)

    return context.multipart
}