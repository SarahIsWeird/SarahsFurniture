package com.sarahisweird.sarahsfurniture.util.arrp

import net.devtech.arrp.json.blockstate.JMultipart
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.blockstate.JVariant

class JStateContext {
    val state = JState()

    fun addMultipart(multipart: JMultipart) {
        state.add(multipart)
    }

    fun addMultipart(builder: JMultipartContext.() -> Unit) {
        state.add(arrpMultipart(builder))
    }

    fun addVariant(variant: JVariant) {
        state.add(variant)
    }

    fun addVariant(builder: JVariantContext.() -> Unit) {
        state.add(arrpVariant(builder))
    }
}

fun arrpState(builder: JStateContext.() -> Unit): JState {
    val context = JStateContext()

    builder.invoke(context)

    return context.state
}