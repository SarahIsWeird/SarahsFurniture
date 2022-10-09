package com.sarahisweird.sarahsfurniture.util.arrp.model

import net.devtech.arrp.json.models.JPosition

class JPositionContext {
    val position = JPosition()

    fun rotation(x: Float, y: Float, z: Float) {
        position.rotation(x, y, z)
    }

    fun rotation(x: Int, y: Int, z: Int) =
        rotation(x.toFloat(), y.toFloat(), z.toFloat())

    fun translation(x: Float, y: Float, z: Float) {
        position.translation(x, y, z)
    }

    fun translation(x: Int, y: Int, z: Int) =
        translation(x.toFloat(), y.toFloat(), z.toFloat())

    fun scale(x: Float, y: Float, z: Float) {
        position.scale(x, y, z)
    }
}

fun arrpPosition(builder: JPositionContext.() -> Unit): JPosition {
    val context = JPositionContext()

    builder.invoke(context)

    return context.position
}