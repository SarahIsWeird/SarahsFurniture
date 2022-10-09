package com.sarahisweird.sarahsfurniture

import com.sarahisweird.sarahsfurniture.util.arrp.JTexturesContext
import com.sarahisweird.sarahsfurniture.util.arrp.addBlockState
import com.sarahisweird.sarahsfurniture.util.arrp.addModel
import com.sarahisweird.sarahsfurniture.util.variants.Variant
import com.sarahisweird.sarahsfurniture.util.variants.VariantUtil
import net.devtech.arrp.api.RRPPreGenEntrypoint
import net.devtech.arrp.api.RuntimeResourcePack
import net.minecraft.util.Identifier

class ArrpGeneration : RRPPreGenEntrypoint {
    companion object {
        val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(SarahsFurniture.MOD_ID)
    }

    override fun pregen() {
        register()
    }

    private fun register() {
        VariantUtil.WOOLS.registerHorizontalFurnitureSingleTexture("armchair")
    }

    private fun List<Variant>.registerHorizontalFurnitureSingleTexture(blockName: String) =
        registerHorizontalFurniture(blockName) {
            variable("0", it.textureName)
            particle(it.textureName)
        }

    private fun List<Variant>.registerHorizontalFurniture(blockName: String, textures: JTexturesContext.(Variant) -> Unit) =
        forEach { variant ->
            val variantBlockName = "${variant.name}_${blockName}"

            val blockIdentifier = Identifier(SarahsFurniture.MOD_ID, variantBlockName)
            val itemIdentifier = Identifier(SarahsFurniture.MOD_ID, "item/$variantBlockName")
            val modelIdentifier = Identifier(SarahsFurniture.MOD_ID, "block/$variantBlockName")

            registerBlockModel(blockIdentifier, modelIdentifier) { textures.invoke(this, variant) }
            registerBlockItemRotationFix(blockIdentifier, itemIdentifier)
            registerHorizontalFurnitureBlockstates(blockIdentifier)
        }

    private fun registerBlockModel(blockIdentifier: Identifier, modelIdentifier: Identifier, textures: JTexturesContext.() -> Unit) =
        RESOURCE_PACK.addModel(blockIdentifier) {
            parent(modelIdentifier)
            textures(textures)
        }

    /**
     * For whatever reason the held item model on the furniture blocks is rotated 90 degrees clockwise.
     * This method registers a fix by rotating the display vale 90 degrees counterclockwise.
     * The values were copied from minecraft:block/block.
     */
    private fun registerBlockItemRotationFix(blockIdentifier: Identifier, itemIdentifier: Identifier) =
        RESOURCE_PACK.addModel(itemIdentifier) {
            parent(blockIdentifier)

            display {
                firstPersonRightHand {
                    rotation(0, 225 - 90, 0)
                    translation(0, 0, 0)
                    scale(0.40f, 0.40f, 0.40f)
                }
            }
        }

    private fun registerHorizontalFurnitureBlockstates(identifier: Identifier) =
        RESOURCE_PACK.addBlockState(identifier) {
            addMultipart {
                addModel(identifier)
                whenCondition("facing", "north")
            }

            addMultipart {
                addModel(identifier) { y(180) }
                whenCondition("facing", "south")
            }

            addMultipart {
                addModel(identifier) { y(90) }
                whenCondition("facing", "east")
            }

            addMultipart {
                addModel(identifier) { y(270) }
                whenCondition("facing", "west")
            }
        }
}