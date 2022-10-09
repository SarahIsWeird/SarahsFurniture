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
}