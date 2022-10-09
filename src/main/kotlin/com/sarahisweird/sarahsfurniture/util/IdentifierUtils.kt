package com.sarahisweird.sarahsfurniture.util

import net.minecraft.util.Identifier

fun String.toIdentifier(namespace: String) =
    Identifier(namespace, this)

fun Identifier.withPathPrefix(prefix: String) =
    Identifier(namespace, prefix + path)

fun Identifier.withPathSuffix(suffix: String) =
    Identifier(namespace, path + suffix)