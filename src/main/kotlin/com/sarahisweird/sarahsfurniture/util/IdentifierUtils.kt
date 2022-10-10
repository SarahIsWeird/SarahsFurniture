package com.sarahisweird.sarahsfurniture.util

import com.sarahisweird.sarahsfurniture.SarahsFurniture
import net.minecraft.util.Identifier

fun String.toId(namespace: String = SarahsFurniture.MOD_ID) =
    toIdentifier(namespace)

fun String.toIdentifier(namespace: String = SarahsFurniture.MOD_ID) =
    Identifier(namespace, this)

fun Identifier.withPathPrefix(prefix: String) =
    Identifier(namespace, prefix + path)

fun Identifier.withPathSuffix(suffix: String) =
    Identifier(namespace, path + suffix)