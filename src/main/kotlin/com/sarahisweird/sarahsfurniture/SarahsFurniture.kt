package com.sarahisweird.sarahsfurniture

import com.sarahisweird.sarahsfurniture.blocks.BlockInit
import com.sarahisweird.sarahsfurniture.util.variants.VariantUtil
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object SarahsFurniture : ModInitializer {
    const val MOD_ID = "sarahsfurniture"

    val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create("$MOD_ID:test")

    val ITEM_GROUP: ItemGroup = FabricItemGroupBuilder.create(Identifier(MOD_ID, "items"))
        .icon { ItemStack(BlockInit.ARMCHAIRS[VariantUtil.COLORS.indexOf("red")]) }
        .build()

    override fun onInitialize() {
        FieldRegistrationHandler.register(BlockInit::class.java, MOD_ID, false)

        RRPCallback.BEFORE_VANILLA.register { it.add(RESOURCE_PACK) }
    }
}