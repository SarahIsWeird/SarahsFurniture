package com.sarahisweird.sarahsfurniture.util

import net.minecraft.util.Identifier

fun String.toIdentifier(namespace: String) =
    Identifier(namespace, this)