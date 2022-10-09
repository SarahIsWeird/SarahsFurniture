package com.sarahisweird.sarahsfurniture.util.arrp

import net.devtech.arrp.json.models.JFace
import net.minecraft.util.math.Direction

class JFaceContext(texture: String) {
    val face = JFace(texture)

    fun uv(x1: Float, y1: Float, x2: Float, y2: Float) {
        face.uv(x1, y1, x2, y2)
    }

    fun cullface(direction: Direction) {
        face.cullface(direction)
    }

    fun rot90() {
        face.rot90()
    }

    fun rot180() {
        face.rot180()
    }

    fun rot270() {
        face.rot270()
    }

    fun tintIndex(index: Int) {
        face.tintIndex(index)
    }
}

fun arrpFace(texture: String, builder: JFaceContext.() -> Unit): JFace {
    val context = JFaceContext(texture)

    builder.invoke(context)

    return context.face
}