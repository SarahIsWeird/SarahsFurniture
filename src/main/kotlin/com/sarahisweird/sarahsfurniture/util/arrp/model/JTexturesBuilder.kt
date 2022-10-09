package com.sarahisweird.sarahsfurniture.util.arrp.model

import net.devtech.arrp.json.models.JTextures

class JTexturesContext {
    val textures = JTextures()

    fun variable(name: String, value: String) {
        textures.`var`(name, value)
    }

    fun particle(value: String) {
        textures.particle(value)
    }

    fun layer0(value: String) {
        textures.layer0(value)
    }

    fun layer1(value: String) {
        textures.layer1(value)
    }

    fun layer2(value: String) {
        textures.layer2(value)
    }

    fun layer3(value: String) {
        textures.layer3(value)
    }

    fun layer4(value: String) {
        textures.layer4(value)
    }
}

fun arrpTextures(builder: JTexturesContext.() -> Unit): JTextures {
    val context = JTexturesContext()

    builder.invoke(context)

    return context.textures
}