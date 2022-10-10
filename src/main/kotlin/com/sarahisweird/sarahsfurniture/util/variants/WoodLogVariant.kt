package com.sarahisweird.sarahsfurniture.util.variants

class WoodLogVariant(name: String, suffix: String) : Variant(name, "minecraft:block/${name}_$suffix") {
    val topTextureName = "minecraft:block/${name}_${suffix}_top"
}
