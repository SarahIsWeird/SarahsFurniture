package com.sarahisweird.sarahsfurniture.util.arrp.model

import net.devtech.arrp.json.models.JElement
import net.minecraft.util.math.Direction.Axis

class JElementContext {
    val element = JElement()

    fun from(x: Float, y: Float, z: Float) {
        element.from(x, y, z)
    }

    fun to(x: Float, y: Float, z: Float) {
        element.to(x, y, z)
    }

    fun rotation(axis: Axis, builder: JRotationContext.() -> Unit) {
        element.rotation(arrpRotation(axis, builder))
    }

    fun shade() {
        element.shade()
    }

    fun faces(builder: JFacesContext.() -> Unit) {
        element.faces(arrpFaces(builder))
    }
}

fun arrpElement(builder: JElementContext.() -> Unit): JElement {
    val context = JElementContext()

    builder.invoke(context)

    return context.element
}