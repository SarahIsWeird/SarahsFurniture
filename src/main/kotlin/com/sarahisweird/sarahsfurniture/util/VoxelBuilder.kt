package com.sarahisweird.sarahsfurniture.util

import net.minecraft.util.math.Box
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes

class VoxelBuilderContext {
    private val _voxels = mutableListOf<VoxelShape>()
    val voxels: List<VoxelShape>
        get() = _voxels

    var direction: Direction? = null
        private set

    var baseDirection: Direction? = null
        private set

    fun cuboid(x: Double, y: Double, z: Double, sizeX: Double, sizeY: Double, sizeZ: Double) {
        _voxels += getVoxelCuboidFromPositionAndSize(x, y, z, x + sizeX, y + sizeY, z + sizeZ)
    }

    fun cuboid(x: Int, y: Int, z: Int, sizeX: Int, sizeY: Int, sizeZ: Int) =
        cuboid(x.toDouble(), y.toDouble(), z.toDouble(), sizeX.toDouble(), sizeY.toDouble(), sizeZ.toDouble())

    fun withBaseDirection(direction: Direction) {
        this.baseDirection = direction
    }

    fun facing(direction: Direction) {
        this.direction = direction
    }

    private fun getVoxelCuboidFromPositionAndSize(x: Double, y: Double, z: Double, sizeX: Double, sizeY: Double, sizeZ: Double) =
        VoxelShapes.cuboid(x / 16.0, y / 16.0, z / 16.0, sizeX / 16.0, sizeY / 16.0, sizeZ / 16.0)
}

fun Box.rotate(from: Direction, to: Direction): Box {
    val angle = to.asRotation() - from.asRotation()

    return rotateAround(Vec3d(0.5, 0.5, 0.5), angle.toDouble())
}

fun Box.rotateAround(pivot: Vec3d, angle: Double): Box {
    if (angle < 0.0) return rotateAround(pivot, (360.0 + angle) % 360.0)
    if (angle > 360.0) return rotateAround(pivot, angle % 360.0)

    return when (angle) {
        0.0 -> this
        90.0 -> rotate90DegreesClockwiseAround(pivot, flipZ = true)
        180.0 -> rotate90DegreesClockwiseAround(pivot, flipX = true).rotate90DegreesClockwiseAround(pivot, flipX = true, flipZ = true)
        270.0 -> rotate90DegreesClockwiseAround(pivot, flipZ = true).rotate90DegreesClockwiseAround(pivot, flipX = true, flipZ = true).rotate90DegreesClockwiseAround(pivot, flipX = true)
        else -> throw RuntimeException("Invalid rotation angle for $angle degree rotation around $pivot")
    }
}

fun Box.rotate90DegreesClockwiseAround(pivot: Vec3d, flipX: Boolean = false, flipZ: Boolean = false): Box {
    val pivotToCenter = center - pivot
    val newCenter = pivot + Vec3d(pivotToCenter.z * if (flipZ) -1.0 else 1.0, pivotToCenter.y, pivotToCenter.x * if (flipX) -1.0 else 1.0)

    return Box.of(newCenter, zLength, yLength, xLength)
}

fun VoxelShape.rotate(from: Direction, to: Direction): VoxelShape =
    boundingBoxes.map { box -> box.rotate(from, to) }
        .map(VoxelShapes::cuboid)
        .reduce(VoxelShapes::union)

fun voxelShape(builder: VoxelBuilderContext.() -> Unit): VoxelShape {
    val context = VoxelBuilderContext()

    builder.invoke(context)

    var voxels = context.voxels

    if (context.direction != null) {
        voxels = voxels.map { it.rotate(context.baseDirection ?: Direction.NORTH, context.direction!!) }
    }

    return voxels.reduce(VoxelShapes::union)
}