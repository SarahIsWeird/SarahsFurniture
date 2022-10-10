package com.sarahisweird.sarahsfurniture

import com.sarahisweird.sarahsfurniture.SarahsFurniture.MOD_ID
import com.sarahisweird.sarahsfurniture.blocks.SFBlocks
import io.wispforest.condensed_creative.registry.CondensedCreativeInitializer
import io.wispforest.condensed_creative.registry.CondensedEntryRegistry
import net.minecraft.block.Block
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class CondensedCreativeInit : CondensedCreativeInitializer {
    override fun onInitializeCondensedEntries(refreshed: Boolean) {
        CondensedEntryRegistry.fromItems(Identifier(MOD_ID, "armchairs"), SFBlocks.ARMCHAIRS[0], SFBlocks.ARMCHAIRS.map(Block::asItem))
            .setTitleString(Text.translatable("sarahsfurniture.armchair.cc_entry"))
            .addItemGroup(SarahsFurniture.ITEM_GROUP)

        CondensedEntryRegistry.fromItems(Identifier(MOD_ID, "dining_tables"), SFBlocks.DINING_TABLES[0], SFBlocks.DINING_TABLES.map(Block::asItem))
            .setTitleString(Text.translatable("sarahsfurniture.dining_table.cc_entry"))
            .addItemGroup(SarahsFurniture.ITEM_GROUP)
    }
}