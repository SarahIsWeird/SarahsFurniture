package com.sarahisweird.sarahsfurniture.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty

open class TableBlock protected constructor(settings: Settings) : Block(settings) {
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
        val northBlock = ctx.world.getBlockState(ctx.blockPos.north()).block.translationKey
        val southBlock = ctx.world.getBlockState(ctx.blockPos.south()).block.translationKey
        val eastBlock = ctx.world.getBlockState(ctx.blockPos.east()).block.translationKey
        val westBlock = ctx.world.getBlockState(ctx.blockPos.west()).block.translationKey
        val northEastBlock = ctx.world.getBlockState(ctx.blockPos.north().east()).block.translationKey
        val northWestBlock = ctx.world.getBlockState(ctx.blockPos.north().west()).block.translationKey
        val southEastBlock = ctx.world.getBlockState(ctx.blockPos.south().east()).block.translationKey
        val southWestBlock = ctx.world.getBlockState(ctx.blockPos.south().west()).block.translationKey

        val hasNorthBlock = northBlock == translationKey
        val hasSouthBlock = southBlock == translationKey
        val hasEastBlock = eastBlock == translationKey
        val hasWestBlock = westBlock == translationKey
        val hasNorthEastBlock = northEastBlock == translationKey
        val hasNorthWestBlock = northWestBlock == translationKey
        val hasSouthEastBlock = southEastBlock == translationKey
        val hasSouthWestBlock = southWestBlock == translationKey

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