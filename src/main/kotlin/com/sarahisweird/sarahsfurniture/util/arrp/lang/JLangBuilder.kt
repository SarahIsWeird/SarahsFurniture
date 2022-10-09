package com.sarahisweird.sarahsfurniture.util.arrp.lang

import net.devtech.arrp.json.lang.JLang
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EntityType
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class JLangContext {
    val lang = JLang()

    class PotionNamesContext {
        var drinkablePotionName: String = ""
        var splashPotionName: String = ""
        var lingeringPotionName: String = ""
        var tippedArrowName: String = ""
    }

    fun entry(entry: String, name: String) {
        lang.entry(entry, name)
    }

    fun item(item: Item, name: String) {
        lang.itemRespect(item, name)
    }

    fun item(itemStack: ItemStack, name: String) {
        lang.item(itemStack, name)
    }

    fun item(id: Identifier, name: String) {
        lang.item(id, name)
    }

    fun block(block: Block, name: String) {
        lang.blockRespect(block, name)
    }

    fun block(id: Identifier, name: String) {
        lang.block(id, name)
    }

    fun fluid(fluid: Fluid, name: String) {
        lang.fluid(fluid, name)
    }

    fun fluid(id: Identifier, name: String) {
        lang.fluid(id, name)
    }

    fun entity(type: EntityType<*>, name: String) {
        lang.entityRespect(type, name)
    }

    fun entity(id: Identifier, name: String) {
        lang.entity(id, name)
    }

    fun enchantment(enchantment: Enchantment, name: String) {
        lang.enchantmentRespect(enchantment, name)
    }

    fun enchantment(id: Identifier, name: String) {
        lang.enchantment(id, name)
    }

    fun itemGroup(id: Identifier, name: String) {
        lang.itemGroup(id, name)
    }

    fun sound(id: Identifier, name: String) {
        lang.sound(id, name)
    }

    fun status(id: Identifier, name: String) {
        lang.status(id, name)
    }

    fun allPotionOf(id: Identifier, effectName: String) {
        lang.allPotionOf(id, effectName)
    }

    fun allPotion(
        id: Identifier,
        drinkablePotionName: String,
        splashPotionName: String,
        lingeringPotionName: String,
        tippedArrowName: String
    ) {
        lang.allPotion(id, drinkablePotionName, splashPotionName, lingeringPotionName, tippedArrowName)
    }

    fun allPotion(id: Identifier, builder: PotionNamesContext.() -> Unit) {
        val context = PotionNamesContext()

        builder.invoke(context)

        allPotion(id, context.drinkablePotionName, context.splashPotionName, context.lingeringPotionName, context.tippedArrowName)
    }

    fun tippedArrow(id: Identifier, name: String) {
        lang.tippedArrow(id, name)
    }

    fun lingeringPotion(id: Identifier, name: String) {
        lang.lingeringPotion(id, name)
    }

    fun splashPotion(id: Identifier, name: String) {
        lang.splashPotion(id, name)
    }

    fun drinkablePotion(id: Identifier, name: String) {
        lang.drinkablePotion(id, name)
    }

    fun biome(id: Identifier, name: String) {
        lang.biome(id, name)
    }
}

fun arrpLang(builder: JLangContext.() -> Unit): JLang {
    val context = JLangContext()

    builder.invoke(context)

    return context.lang
}