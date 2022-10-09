package com.sarahisweird.sarahsfurniture.util.variants

object VariantUtil {
    val COLORS = listOf(
        "white",
        "orange",
        "magenta",
        "light_blue",
        "yellow",
        "lime",
        "pink",
        "gray",
        "light_gray",
        "cyan",
        "purple",
        "blue",
        "brown",
        "green",
        "red",
        "black"
    )

    val WOOD_TYPES = listOf(
        "oak",
        "spruce",
        "birch",
        "jungle",
        "acacia",
        "dark_oak",
        "crimson",
        "warped",
        "mangrove"
    )

    val WOOLS = COLORS.map(::WoolVariant)
    val WOOD_LOGS = WOOD_TYPES.map(::WoodLogVariant)
    val WOOD_PLANKS = WOOD_TYPES.map(::WoodPlanksVariant)

    val COLOR_TRANSLATIONS = mapOf(
        "en_us" to listOf(
            "White",
            "Orange",
            "Magenta",
            "Light Blue",
            "Yellow",
            "Lime",
            "Pink",
            "Gray",
            "Light Gray",
            "Cyan",
            "Purple",
            "Blue",
            "Brown",
            "Green",
            "Red",
            "Black"
        )
    )

    val WOOD_TRANSLATIONS = mapOf(
        "en_us" to listOf(
            "Oak",
            "Spruce",
            "Birch",
            "Jungle",
            "Acacia",
            "Dark Oak",
            "Crimson",
            "Warped",
            "Mangrove"
        )
    )
}