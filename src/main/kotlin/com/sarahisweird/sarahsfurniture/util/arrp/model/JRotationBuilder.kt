package com.sarahisweird.sarahsfurniture.util.arrp.model

import net.devtech.arrp.json.models.JRotation
import net.minecraft.util.math.Direction.Axis

class JRotationContext(axis: Axis) {
    val rotation = JRotation(axis)

    fun origin(x: Float, y: Float, z: Float) {
        rotation.origin(x, y, z)
    }

    fun angle(rotationAngle: Float) {
        rotation.angle(rotationAngle)
    }

    fun rescale() {
        rotation.rescale()
    }
}

fun arrpRotation(axis: Axis, builder: JRotationContext.() -> Unit): JRotation {
    val context = JRotationContext(axis)

    builder.invoke(context)

    return context.rotation
}