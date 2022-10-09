package com.sarahisweird.sarahsfurniture.blocks

import com.sarahisweird.sarahsfurniture.util.voxelShape
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class ArmchairBlock : Block(FabricBlockSettings.of(Material.WOOL).strength(0.8f).sounds(BlockSoundGroup.WOOL)) {
    companion object {
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING
    }

    init {
        defaultState = stateManager.defaultState.with(FACING, Direction.NORTH)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState =
        defaultState.with(FACING, ctx.playerFacing.opposite)

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