package com.sarahisweird.sarahsfurniture.util.arrp.tags

import net.devtech.arrp.json.tags.JTag
import net.minecraft.util.Identifier

class JTagContext {
    val tag = JTag()

    fun shouldReplace() {
        tag.replace()
    }

    fun add(identifier: Identifier) {
        tag.add(identifier)
    }

    fun addTag(identifier: Identifier) {
        tag.tag(identifier)
    }
}

fun arrpTag(builder: JTagContext.() -> Unit): JTag {
    val context = JTagContext()

    builder.invoke(context)

    return context.tag
}