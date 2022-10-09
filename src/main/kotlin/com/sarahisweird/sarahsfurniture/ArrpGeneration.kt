package com.sarahisweird.sarahsfurniture

import com.sarahisweird.sarahsfurniture.SarahsFurniture.MOD_ID
import com.sarahisweird.sarahsfurniture.util.arrp.addBlockState
import com.sarahisweird.sarahsfurniture.util.arrp.addLang
import com.sarahisweird.sarahsfurniture.util.arrp.addLootTable
import com.sarahisweird.sarahsfurniture.util.arrp.addModel
import com.sarahisweird.sarahsfurniture.util.arrp.model.JTexturesContext
import com.sarahisweird.sarahsfurniture.util.toIdentifier
import com.sarahisweird.sarahsfurniture.util.variants.Variant
import com.sarahisweird.sarahsfurniture.util.variants.VariantUtil
import net.devtech.arrp.api.RRPPreGenEntrypoint
import net.devtech.arrp.api.RuntimeResourcePack
import net.minecraft.util.Identifier

class ArrpGeneration : RRPPreGenEntrypoint {
    companion object {
        val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(MOD_ID)
    }

    override fun pregen() {
        registerBlocks()
        registerLangEntries()
        registerLootTables()
    }

    private fun registerBlocks() {
        VariantUtil.WOOLS.registerHorizontalFurnitureSingleTexture("armchair")
    }

    private fun registerLangEntries() {
        RESOURCE_PACK.addLang("en_us".toIdentifier(MOD_ID)) {
            VariantUtil.COLORS.zip(VariantUtil.TRANSLATIONS["en_us"]!!).forEach { (name, displayName) ->
                block(Identifier(MOD_ID, "${name}_armchair"), "$displayName Armchair")
            }
        }
    }

    private fun registerLootTables() {
        VariantUtil.WOOLS.registerDefaultLootTables("armchair")
    }

    private fun List<Variant>.registerHorizontalFurnitureSingleTexture(blockName: String) =
        registerHorizontalFurniture(blockName) {
            variable("0", it.textureName)
            particle(it.textureName)
        }

    private fun List<Variant>.registerHorizontalFurniture(blockName: String, textures: JTexturesContext.(Variant) -> Unit) =
        forEach { variant ->
            val variantBlockName = "${variant.name}_${blockName}"

            val blockIdentifier = Identifier(MOD_ID, variantBlockName)
            val itemIdentifier = Identifier(MOD_ID, "item/$variantBlockName")
            val parentModelIdentifier = Identifier(MOD_ID, "block/$blockName")

            registerBlockModel(blockIdentifier, parentModelIdentifier) { textures.invoke(this, variant) }
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

    private fun List<Variant>.registerDefaultLootTables(blockName: String) =
        forEach { variant ->
            RESOURCE_PACK.addLootTable(Identifier(MOD_ID, "blocks/${variant.name}_$blockName"), "minecraft:block") {
                pool {
                    rolls(1)

                    entry {
                        type("minecraft:item")
                        name("$MOD_ID:${variant.name}_$blockName")
                    }

                    condition("minecraft:survives_explosion")
                }
            }
        }
}