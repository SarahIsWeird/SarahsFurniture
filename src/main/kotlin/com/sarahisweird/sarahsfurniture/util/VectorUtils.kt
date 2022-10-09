package com.sarahisweird.sarahsfurniture.util

import net.minecraft.util.math.Vec3d

operator fun Vec3d.plus(other: Vec3d): Vec3d =
    add(other)

operator fun Vec3d.minus(other: Vec3d): Vec3d =
    subtract(other)

operator fun Vec3d.times(other: Vec3d): Vec3d =
    multiply(other)

operator fun Vec3d.times(factor: Double): Vec3d =
    multiply(factor)