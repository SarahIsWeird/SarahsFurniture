package com.sarahisweird.sarahsfurniture.blocks

import com.sarahisweird.sarahsfurniture.util.voxelShape
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.ShapeContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class DiningTableBlock : TableBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)) {
    @Suppress("OVERRIDE_DEPRECATION")
    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape =
        voxelShape {
            // Surface
            cuboid(0, 14, 0, 16, 2, 16)

            // Legs
            if (state[NORTH_EAST] == true) cuboid(12, 0, 2, 2, 14, 2)
            if (state[NORTH_WEST] == true) cuboid(2, 0, 2, 2, 14, 2)
            if (state[SOUTH_EAST] == true) cuboid(12, 0, 12, 2, 14, 2)
            if (state[SOUTH_WEST] == true) cuboid(2, 0, 12, 2, 14, 2)
        }
}