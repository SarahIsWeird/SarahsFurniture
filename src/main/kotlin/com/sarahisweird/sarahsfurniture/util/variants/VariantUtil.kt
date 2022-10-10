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

    enum class LogType(val suffix: String) {
        LOG("log"),
        STEM("stem")
    }

    val WOOD_TYPES = mapOf(
        "oak" to LogType.LOG,
        "spruce" to LogType.LOG,
        "birch" to LogType.LOG,
        "jungle" to LogType.LOG,
        "acacia" to LogType.LOG,
        "dark_oak" to LogType.LOG,
        "crimson" to LogType.STEM,
        "warped" to LogType.STEM,
        "mangrove" to LogType.LOG
    )

    val WOOLS = COLORS.map(::WoolVariant)
    val WOOD_LOGS = WOOD_TYPES.map { (name, type) -> WoodLogVariant(name, type.suffix) }
    val WOOD_PLANKS = WOOD_TYPES.map { (name, _) -> WoodPlanksVariant(name) }

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