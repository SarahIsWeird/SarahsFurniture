package com.sarahisweird.sarahsfurniture.blocks

import com.sarahisweird.sarahsfurniture.util.voxelShape
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class ArmchairBlock : HorizontalFurnitureBlock(FabricBlockSettings.of(Material.WOOL)) {
    @Suppress("OVERRIDE_DEPRECATION")
    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape =
        voxelShape {
            cuboid(0, 0, 0, 16, 4, 16)
            cuboid(0, 4, 0, 3, 4, 16)
            cuboid(13, 4, 0, 3, 4, 16)
            cuboid(3, 4, 12, 10, 4, 4)
            cuboid(1, 8, 12, 14, 8, 4)

            facing(state[FACING])
        }
}