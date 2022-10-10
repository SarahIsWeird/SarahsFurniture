package com.sarahisweird.sarahsfurniture

import com.sarahisweird.sarahsfurniture.blocks.SFBlocks
import com.sarahisweird.sarahsfurniture.util.toId
import io.wispforest.condensed_creative.registry.CondensedCreativeInitializer
import io.wispforest.condensed_creative.registry.CondensedEntryRegistry
import net.minecraft.text.Text

class CondensedCreativeInit : CondensedCreativeInitializer {
    override fun onInitializeCondensedEntries(refreshed: Boolean) {
        CondensedEntryRegistry.fromBlockTag("armchairs".toId(), SFBlocks.ARMCHAIRS[0], SFTags.ARMCHAIRS)
            .setTitleString(Text.translatable("sarahsfurniture.armchair.cc_entry"))
            .addItemGroup(SarahsFurniture.ITEM_GROUP)

        CondensedEntryRegistry.fromBlockTag("dining_tables".toId(), SFBlocks.DINING_TABLES[0], SFTags.DINING_TABLES)
            .setTitleString(Text.translatable("sarahsfurniture.dining_table.cc_entry"))
            .addItemGroup(SarahsFurniture.ITEM_GROUP)
    }
}