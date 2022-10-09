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

    val WOOLS = COLORS.map(::WoolVariant)

    val TRANSLATIONS = mapOf(
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
}