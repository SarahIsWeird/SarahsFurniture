package com.sarahisweird.sarahsfurniture.blocks

import com.sarahisweird.sarahsfurniture.SarahsFurniture
import com.sarahisweird.sarahsfurniture.util.variants.VariantUtil
import io.wispforest.owo.registration.annotations.IterationIgnored
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.blockstate.JWhen
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.models.JTextures
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
        val armchairParentModel = "sarahsfurniture:block/armchair"

        VariantUtil.WOOLS.mapIndexed { i, wool ->
            val blockIdentifier = Identifier(SarahsFurniture.MOD_ID, "${wool.name}_armchair")
            val itemIdentifier = Identifier(SarahsFurniture.MOD_ID, "item/${wool.name}_armchair")

            Registry.register(Registry.BLOCK, blockIdentifier, ARMCHAIRS[i])
            Registry.register(Registry.ITEM, blockIdentifier, BlockItem(ARMCHAIRS[i], FabricItemSettings().group(SarahsFurniture.ITEM_GROUP)))

            SarahsFurniture.RESOURCE_PACK.addModel(JModel.model(armchairParentModel).textures(JTextures().`var`("0", wool.textureName).particle(wool.textureName)), blockIdentifier)
            SarahsFurniture.RESOURCE_PACK.addModel(JModel.model(blockIdentifier), itemIdentifier)

            val model = JState.model(blockIdentifier)

            val north = JState.multipart(model.clone()).`when`(JWhen().add("facing", "north"))
            val south = JState.multipart(model.clone().y(180)).`when`(JWhen().add("facing", "south"))
            val east = JState.multipart(model.clone().y(90)).`when`(JWhen().add("facing", "east"))
            val west = JState.multipart(model.clone().y(270)).`when`(JWhen().add("facing", "west"))

            val states = JState.state(north, south, east, west)

            SarahsFurniture.RESOURCE_PACK.addBlockState(states, blockIdentifier)
        }
    }
}