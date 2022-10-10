package com.sarahisweird.sarahsfurniture

import com.sarahisweird.sarahsfurniture.SarahsFurniture.MOD_ID
import com.sarahisweird.sarahsfurniture.util.arrp.*
import com.sarahisweird.sarahsfurniture.util.arrp.model.JTexturesContext
import com.sarahisweird.sarahsfurniture.util.arrp.tags.JTagContext
import com.sarahisweird.sarahsfurniture.util.toIdentifier
import com.sarahisweird.sarahsfurniture.util.variants.Variant
import com.sarahisweird.sarahsfurniture.util.variants.VariantUtil
import com.sarahisweird.sarahsfurniture.util.withPathPrefix
import com.sarahisweird.sarahsfurniture.util.withPathSuffix
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
        registerTags()
        registerRecipes()
    }

    private fun registerBlocks() {
        VariantUtil.WOOLS.registerHorizontalFurnitureSingleTexture("armchair")

        VariantUtil.WOOD_LOGS.registerTableLegs("dining_table")
        VariantUtil.WOOD_PLANKS.registerTableSurfaces("dining_table")

        val diningTableIdentifier = Identifier(MOD_ID, "dining_table")
        val diningTableSurfaceIdentifier = diningTableIdentifier.withPathSuffix("_surface")
        val diningTableLegIdentifier = diningTableIdentifier.withPathSuffix("_leg")

        for (log in VariantUtil.WOOD_LOGS) {
            for (planks in VariantUtil.WOOD_PLANKS) {
                val blockVariant = diningTableIdentifier.withPathPrefix("${planks.name}_${log.name}_")
                val surfaceVariant = diningTableSurfaceIdentifier.withPathPrefix("${planks.name}_")
                val legVariant = diningTableLegIdentifier.withPathPrefix("${log.name}_")

                registerTableBlockstates(blockVariant, surfaceVariant, legVariant)

                RESOURCE_PACK.addModel(blockVariant.withPathPrefix("item/")) {
                    parent(Identifier(MOD_ID, "item/dining_table_item"))

                    textures {
                        variable("surface", planks.textureName)
                        variable("leg", log.textureName)
                        particle(planks.textureName)
                    }
                }
            }
        }
    }

    private fun registerLangEntries() {
        RESOURCE_PACK.addLang("en_us".toIdentifier(MOD_ID)) {
            VariantUtil.COLORS.zip(VariantUtil.COLOR_TRANSLATIONS["en_us"]!!).forEach { (name, displayName) ->
                block(Identifier(MOD_ID, "${name}_armchair"), "$displayName Armchair")
            }

            val diningTableIdentifier = Identifier(MOD_ID, "dining_table")

            VariantUtil.WOOD_PLANKS.zip(VariantUtil.WOOD_TRANSLATIONS["en_us"]!!).forEach { (planks, displayName) ->
                for (log in VariantUtil.WOOD_LOGS) {
                    val blockVariant = diningTableIdentifier.withPathPrefix("${planks.name}_${log.name}_")

                    block(blockVariant,  "$displayName Dining Table")
                }
            }
        }
    }

    private fun registerLootTables() {
        VariantUtil.WOOLS.registerDefaultLootTables("armchair")

        for (log in VariantUtil.WOOD_LOGS) {
            VariantUtil.WOOD_PLANKS.registerDefaultLootTables("${log.name}_dining_table")
        }
    }

    private fun registerTags() {
        RESOURCE_PACK.addTag(Identifier("minecraft", "blocks/mineable/axe")) {
            addAll(Identifier(MOD_ID, "dining_table"), VariantUtil.WOOD_LOGS, VariantUtil.WOOD_PLANKS)

        }

        RESOURCE_PACK.addTag(Identifier(MOD_ID, "blocks/dining_tables")) {
            addAll(Identifier(MOD_ID, "dining_table"), VariantUtil.WOOD_LOGS, VariantUtil.WOOD_PLANKS)
        }
    }

    private fun JTagContext.addAll(baseId: Identifier, outerVariants: List<Variant>, innerVariants: List<Variant>) {
        for (outer in outerVariants) {
            val variant1Id = baseId.withPathPrefix("${outer.name}_")

            for (inner in innerVariants) {
                add(variant1Id.withPathPrefix("${inner.name}_"))
            }
        }
    }

    private fun registerRecipes() {
        VariantUtil.WOOLS.forEach { variant ->
            val woolId = Identifier("minecraft", "${variant.name}_wool")
            val armchairId = Identifier(MOD_ID, "${variant.name}_armchair")

            RESOURCE_PACK.addShapedRecipe(armchairId) {
                pattern(
                    " W ",
                    "WWW",
                    "WWW"
                )

                key("W", woolId)
                result(armchairId)
            }
        }

        for (log in VariantUtil.WOOD_LOGS) {
            val logId = Identifier("minecraft", "${log.name}_${log.suffix}")

            for (planks in VariantUtil.WOOD_PLANKS) {
                val planksSlabId = Identifier("minecraft", "${planks.name}_slab")
                val tableId = Identifier(MOD_ID, "${planks.name}_${log.name}_dining_table")

                RESOURCE_PACK.addShapedRecipe(tableId) {
                    pattern(
                        "SSS",
                        "L L",
                        "L L"
                    )

                    key("S", planksSlabId)
                    key("L", logId)
                    result(tableId, 4)
                }
            }
        }
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

    private fun List<Variant>.registerTableLegs(blockName: String) =
        forEach { variant ->
            val parentIdentifier = Identifier(MOD_ID, "${blockName}_leg")
            val modelIdentifier = parentIdentifier.withPathPrefix("${variant.name}_")

            registerBlockModel(modelIdentifier, parentIdentifier.withPathPrefix("block/")) {
                variable("leg", variant.textureName)
                particle(variant.textureName)
            }
        }

    private fun List<Variant>.registerTableSurfaces(blockName: String) =
        forEach { variant ->
            val parentIdentifier = Identifier(MOD_ID, "${blockName}_surface")
            val modelIdentifier = parentIdentifier.withPathPrefix("${variant.name}_")

            registerBlockModel(modelIdentifier, parentIdentifier.withPathPrefix("block/")) {
                variable("surface", variant.textureName)
                particle(variant.textureName)
            }
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

    private fun registerTableBlockstates(identifier: Identifier, surfaceIdentifier: Identifier, legIdentifier: Identifier) =
        RESOURCE_PACK.addBlockState(identifier) {
            addMultipartModel(surfaceIdentifier)

            addMultipart {
                addModel(legIdentifier)
                whenCondition("north_west", "true")
            }

            addMultipart {
                addModel(legIdentifier) { y(90) }
                whenCondition("north_east", "true")
            }

            addMultipart {
                addModel(legIdentifier) { y(180) }
                whenCondition("south_east", "true")
            }

            addMultipart {
                addModel(legIdentifier) { y(270) }
                whenCondition("south_west", "true")
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