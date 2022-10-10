package com.sarahisweird.sarahsfurniture.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.tag.TagKey
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess

open class TableBlock protected constructor(settings: Settings, private val tableTag: TagKey<Block>) : Block(settings) {
    companion object {
        val NORTH_EAST: BooleanProperty = BooleanProperty.of("north_east")
        val NORTH_WEST: BooleanProperty = BooleanProperty.of("north_west")
        val SOUTH_EAST: BooleanProperty = BooleanProperty.of("south_east")
        val SOUTH_WEST: BooleanProperty = BooleanProperty.of("south_west")
    }

    init {
        defaultState = stateManager.defaultState
            .with(NORTH_EAST, true)
            .with(NORTH_WEST, true)
            .with(SOUTH_EAST, true)
            .with(SOUTH_WEST, true)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(NORTH_EAST).add(NORTH_WEST).add(SOUTH_EAST).add(SOUTH_WEST)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return getBlockStateForNeighbors(ctx.world, ctx.blockPos)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun getStateForNeighborUpdate(state: BlockState, direction: Direction, neighborState: BlockState, world: WorldAccess, pos: BlockPos, neighborPos: BlockPos): BlockState {
        return getBlockStateForNeighbors(world, pos)
    }

    private fun getBlockStateForNeighbors(world: BlockView, blockPos: BlockPos): BlockState {
        val hasNorthBlock = world.getBlockState(blockPos.north()).isIn(tableTag)
        val hasSouthBlock = world.getBlockState(blockPos.south()).isIn(tableTag)
        val hasEastBlock = world.getBlockState(blockPos.east()).isIn(tableTag)
        val hasWestBlock = world.getBlockState(blockPos.west()).isIn(tableTag)
        val hasNorthEastBlock = world.getBlockState(blockPos.north().east()).isIn(tableTag)
        val hasNorthWestBlock = world.getBlockState(blockPos.north().west()).isIn(tableTag)
        val hasSouthEastBlock = world.getBlockState(blockPos.south().east()).isIn(tableTag)
        val hasSouthWestBlock = world.getBlockState(blockPos.south().west()).isIn(tableTag)

        return defaultState
            .with(NORTH_EAST, hasLeg(hasNorthBlock, hasEastBlock, hasNorthEastBlock))
            .with(NORTH_WEST, hasLeg(hasNorthBlock, hasWestBlock, hasNorthWestBlock))
            .with(SOUTH_EAST, hasLeg(hasSouthBlock, hasEastBlock, hasSouthEastBlock))
            .with(SOUTH_WEST, hasLeg(hasSouthBlock, hasWestBlock, hasSouthWestBlock))
    }

    /**
     * Don't even ask. I made a truth table and this is an equation that fits it.
     */
    private fun hasLeg(a: Boolean, b: Boolean, ab: Boolean) =
        !(a || b) || (a && b && !ab)
}