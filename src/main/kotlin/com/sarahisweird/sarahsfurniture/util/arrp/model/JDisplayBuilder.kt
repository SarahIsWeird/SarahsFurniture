package com.sarahisweird.sarahsfurniture.util.arrp.model

import net.devtech.arrp.json.models.JDisplay

class JDisplayContext {
    val display = JDisplay()

    fun firstPersonRightHand(builder: JPositionContext.() -> Unit) {
        display.setFirstperson_righthand(arrpPosition(builder))
    }

    fun thirdPersonRightHand(builder: JPositionContext.() -> Unit) {
        display.setThirdperson_righthand(arrpPosition(builder))
    }

    fun firstPersonLeftHand(builder: JPositionContext.() -> Unit) {
        display.setFirstperson_lefthand(arrpPosition(builder))
    }

    fun thirdPersonLeftHand(builder: JPositionContext.() -> Unit) {
        display.setThirdperson_lefthand(arrpPosition(builder))
    }

    fun ground(builder: JPositionContext.() -> Unit) {
        display.setGround(arrpPosition(builder))
    }

    fun fixed(builder: JPositionContext.() -> Unit) {
        display.setFixed(arrpPosition(builder))
    }

    fun gui(builder: JPositionContext.() -> Unit) {
        display.setGui(arrpPosition(builder))
    }

    fun head(builder: JPositionContext.() -> Unit) {
        display.setHead(arrpPosition(builder))
    }
}

fun arrpDisplay(builder: JDisplayContext.() -> Unit): JDisplay {
    val context = JDisplayContext()

    builder.invoke(context)

    return context.display
}