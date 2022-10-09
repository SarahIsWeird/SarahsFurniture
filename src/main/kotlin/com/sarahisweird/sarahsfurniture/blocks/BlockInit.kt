package com.sarahisweird.sarahsfurniture.blocks

import com.sarahisweird.sarahsfurniture.SarahsFurniture
import com.sarahisweird.sarahsfurniture.util.variants.VariantUtil
import io.wispforest.owo.registration.annotations.IterationIgnored
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class BlockInit : BlockRegistryContainer {
    companion object {
        @IterationIgnored
        val ARMCHAIRS = VariantUtil.WOOLS.map { ArmchairBlock() }
    }

    override fun createBlockItem(block: Block, identifier: String) =
        BlockItem(block, FabricItemSettings().group(SarahsFurniture.ITEM_GROUP))

    override fun afterFieldProcessing() {
        ARMCHAIRS.forEachIndexed { i, chair ->
            val blockIdentifier = Identifier(SarahsFurniture.MOD_ID, "${VariantUtil.COLORS[i]}_armchair")

            Registry.register(Registry.BLOCK, blockIdentifier, chair)
            Registry.register(Registry.ITEM, blockIdentifier, BlockItem(chair, FabricItemSettings().group(SarahsFurniture.ITEM_GROUP)))
        }
    }
}