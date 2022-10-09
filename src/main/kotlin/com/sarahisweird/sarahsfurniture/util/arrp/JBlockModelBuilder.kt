package com.sarahisweird.sarahsfurniture.util.arrp

import net.devtech.arrp.json.blockstate.JBlockModel
import net.minecraft.util.Identifier

class JBlockModelContext(identifier: Identifier) {
    val blockModel = JBlockModel(identifier)

    fun x(x: Int) {
        blockModel.x(x)
    }

    fun y(y: Int) {
        blockModel.y(y)
    }

    fun uvlock() {
        blockModel.uvlock()
    }

    fun weight(weight: Int) {
        blockModel.weight(weight)
    }
}

fun arrpBlockModel(identifier: Identifier, builder: JBlockModelContext.() -> Unit): JBlockModel {
    val context = JBlockModelContext(identifier)

    builder.invoke(context)

    return context.blockModel
}