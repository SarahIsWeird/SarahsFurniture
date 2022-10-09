package com.sarahisweird.sarahsfurniture.util.arrp

import com.sarahisweird.sarahsfurniture.util.arrp.blockstate.JStateContext
import com.sarahisweird.sarahsfurniture.util.arrp.blockstate.arrpState
import com.sarahisweird.sarahsfurniture.util.arrp.lang.JLangContext
import com.sarahisweird.sarahsfurniture.util.arrp.lang.arrpLang
import com.sarahisweird.sarahsfurniture.util.arrp.model.JModelContext
import com.sarahisweird.sarahsfurniture.util.arrp.model.arrpModel
import net.devtech.arrp.api.RuntimeResourcePack
import net.minecraft.util.Identifier

fun RuntimeResourcePack.addModel(identifier: Identifier, builder: JModelContext.() -> Unit): ByteArray =
    addModel(arrpModel(builder), identifier)

fun RuntimeResourcePack.addBlockState(identifier: Identifier, builder: JStateContext.() -> Unit): ByteArray =
    addBlockState(arrpState(builder), identifier)

fun RuntimeResourcePack.addLang(identifier: Identifier, builder: JLangContext.() -> Unit): ByteArray =
    addLang(identifier, arrpLang(builder))