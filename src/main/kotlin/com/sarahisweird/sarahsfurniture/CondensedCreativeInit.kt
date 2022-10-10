package com.sarahisweird.sarahsfurniture

import com.sarahisweird.sarahsfurniture.SarahsFurniture.MOD_ID
import com.sarahisweird.sarahsfurniture.blocks.SFBlocks
import io.wispforest.condensed_creative.registry.CondensedCreativeInitializer
import io.wispforest.condensed_creative.registry.CondensedEntryRegistry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class CondensedCreativeInit : CondensedCreativeInitializer {
    override fun onInitializeCondensedEntries(refreshed: Boolean) {
        CondensedEntryRegistry.fromBlockTag(Identifier(MOD_ID, "armchairs"), SFBlocks.ARMCHAIRS[0], SFTags.ARMCHAIRS)
            .setTitleString(Text.translatable("sarahsfurniture.armchair.cc_entry"))
            .addItemGroup(SarahsFurniture.ITEM_GROUP)

        CondensedEntryRegistry.fromBlockTag(Identifier(MOD_ID, "dining_tables"), SFBlocks.DINING_TABLES[0], SFTags.DINING_TABLES)
            .setTitleString(Text.translatable("sarahsfurniture.dining_table.cc_entry"))
            .addItemGroup(SarahsFurniture.ITEM_GROUP)
    }
}