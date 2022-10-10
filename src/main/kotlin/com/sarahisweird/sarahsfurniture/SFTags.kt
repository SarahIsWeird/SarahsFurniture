package com.sarahisweird.sarahsfurniture

import net.minecraft.block.Block
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object SFTags {
    val ARMCHAIRS: TagKey<Block> = TagKey.of(Registry.BLOCK_KEY, Identifier(SarahsFurniture.MOD_ID, "armchairs"))
    val DINING_TABLES: TagKey<Block> = TagKey.of(Registry.BLOCK_KEY, Identifier(SarahsFurniture.MOD_ID, "dining_tables"))
}