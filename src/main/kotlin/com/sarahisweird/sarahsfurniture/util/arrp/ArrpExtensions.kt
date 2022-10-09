package com.sarahisweird.sarahsfurniture.util.arrp

import net.devtech.arrp.api.RuntimeResourcePack
import net.minecraft.util.Identifier

fun RuntimeResourcePack.addModel(identifier: Identifier, builder: JModelContext.() -> Unit): ByteArray =
    addModel(arrpModel(builder), identifier)

fun RuntimeResourcePack.addBlockState(identifier: Identifier, builder: JStateContext.() -> Unit): ByteArray =
    addBlockState(arrpState(builder), identifier)