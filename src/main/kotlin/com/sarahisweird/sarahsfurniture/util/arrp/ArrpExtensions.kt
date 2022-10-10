package com.sarahisweird.sarahsfurniture.util.arrp

import com.sarahisweird.sarahsfurniture.util.arrp.blockstate.JStateContext
import com.sarahisweird.sarahsfurniture.util.arrp.blockstate.arrpState
import com.sarahisweird.sarahsfurniture.util.arrp.lang.JLangContext
import com.sarahisweird.sarahsfurniture.util.arrp.lang.arrpLang
import com.sarahisweird.sarahsfurniture.util.arrp.loot.JLootTableContext
import com.sarahisweird.sarahsfurniture.util.arrp.loot.arrpLootTable
import com.sarahisweird.sarahsfurniture.util.arrp.model.JModelContext
import com.sarahisweird.sarahsfurniture.util.arrp.model.arrpModel
import com.sarahisweird.sarahsfurniture.util.arrp.tags.JTagContext
import com.sarahisweird.sarahsfurniture.util.arrp.tags.arrpTag
import net.devtech.arrp.api.RuntimeResourcePack
import net.minecraft.util.Identifier

fun RuntimeResourcePack.addModel(identifier: Identifier, builder: JModelContext.() -> Unit): ByteArray =
    addModel(arrpModel(builder), identifier)

fun RuntimeResourcePack.addBlockState(identifier: Identifier, builder: JStateContext.() -> Unit): ByteArray =
    addBlockState(arrpState(builder), identifier)

fun RuntimeResourcePack.addLang(identifier: Identifier, builder: JLangContext.() -> Unit): ByteArray =
    addLang(identifier, arrpLang(builder))

fun RuntimeResourcePack.addLootTable(identifier: Identifier, type: String, builder: JLootTableContext.() -> Unit): ByteArray =
    addLootTable(identifier, arrpLootTable(type, builder))

fun RuntimeResourcePack.addTag(identifier: Identifier, builder: JTagContext.() -> Unit): ByteArray =
    addTag(identifier, arrpTag(builder))