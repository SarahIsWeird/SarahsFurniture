package com.sarahisweird.sarahsfurniture

import com.sarahisweird.sarahsfurniture.util.arrp.addBlockState
import com.sarahisweird.sarahsfurniture.util.arrp.addModel
import com.sarahisweird.sarahsfurniture.util.variants.VariantUtil
import net.devtech.arrp.api.RRPPreGenEntrypoint
import net.devtech.arrp.api.RuntimeResourcePack
import net.minecraft.util.Identifier

class ArrpGeneration : RRPPreGenEntrypoint {
    companion object {
        val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(SarahsFurniture.MOD_ID)
    }

    override fun pregen() {
        register(RESOURCE_PACK)
    }

    private fun register(resourcePack: RuntimeResourcePack) {
        val armchairParentModel = "sarahsfurniture:block/armchair"

        VariantUtil.WOOLS.map { wool ->
            val blockIdentifier = Identifier(SarahsFurniture.MOD_ID, "${wool.name}_armchair")
            val itemIdentifier = Identifier(SarahsFurniture.MOD_ID, "item/${wool.name}_armchair")

            resourcePack.addModel(blockIdentifier) {
                parent(armchairParentModel)

                textures {
                    variable("0", wool.textureName)
                    particle(wool.textureName)
                }
            }

            resourcePack.addModel(itemIdentifier) {
                parent(blockIdentifier)

                display {
                    firstPersonRightHand {
                        rotation(0, 225 - 90, 0)
                        translation(0, 0, 0)
                        scale(0.40f, 0.40f, 0.40f)
                    }
                }
            }

            resourcePack.addBlockState(blockIdentifier) {
                addMultipart {
                    addModel(blockIdentifier)
                    whenCondition("facing", "north")
                }

                addMultipart {
                    addModel(blockIdentifier) { y(180) }
                    whenCondition("facing", "south")
                }

                addMultipart {
                    addModel(blockIdentifier) { y(90) }
                    whenCondition("facing", "east")
                }

                addMultipart {
                    addModel(blockIdentifier) { y(270) }
                    whenCondition("facing", "west")
                }
            }
        }
    }
}