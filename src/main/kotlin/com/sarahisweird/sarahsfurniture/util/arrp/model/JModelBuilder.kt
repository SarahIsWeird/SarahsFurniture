package com.sarahisweird.sarahsfurniture.util.arrp.model

import net.devtech.arrp.json.models.JModel
import net.minecraft.util.Identifier

class JModelContext {
    val model = JModel()

    fun parent(parentModel: String) {
        model.parent(parentModel)
    }

    fun parent(parentModel: Identifier) =
        parent(parentModel.toString())

    fun noAmbientOcclusion() {
        model.noAmbientOcclusion()
    }

    fun display(builder: JDisplayContext.() -> Unit) {
        model.display(arrpDisplay(builder))
    }

    fun textures(builder: JTexturesContext.() -> Unit) {
        model.textures(arrpTextures(builder))
    }
}

fun arrpModel(builder: JModelContext.() -> Unit): JModel {
    val context = JModelContext()

    builder.invoke(context)

    return context.model
}