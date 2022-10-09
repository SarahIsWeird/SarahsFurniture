package com.sarahisweird.sarahsfurniture.util.arrp

import net.devtech.arrp.json.models.JFaces
import net.minecraft.util.Identifier

class JFacesContext {
    val faces = JFaces()

    fun up(texture: String, builder: JFaceContext.() -> Unit) {
        faces.up(arrpFace(texture, builder))
    }

    fun up(texture: Identifier, builder: JFaceContext.() -> Unit) =
        up(texture.toString(), builder)

    fun down(texture: String, builder: JFaceContext.() -> Unit) {
        faces.down(arrpFace(texture, builder))
    }

    fun down(texture: Identifier, builder: JFaceContext.() -> Unit) =
        down(texture.toString(), builder)

    fun north(texture: String, builder: JFaceContext.() -> Unit) {
        faces.north(arrpFace(texture, builder))
    }

    fun north(texture: Identifier, builder: JFaceContext.() -> Unit) =
        north(texture.toString(), builder)

    fun south(texture: String, builder: JFaceContext.() -> Unit) {
        faces.south(arrpFace(texture, builder))
    }

    fun south(texture: Identifier, builder: JFaceContext.() -> Unit) =
        south(texture.toString(), builder)

    fun east(texture: String, builder: JFaceContext.() -> Unit) {
        faces.east(arrpFace(texture, builder))
    }

    fun east(texture: Identifier, builder: JFaceContext.() -> Unit) =
        east(texture.toString(), builder)

    fun west(texture: String, builder: JFaceContext.() -> Unit) {
        faces.west(arrpFace(texture, builder))
    }

    fun west(texture: Identifier, builder: JFaceContext.() -> Unit) =
        west(texture.toString(), builder)
}

fun arrpFaces(builder: JFacesContext.() -> Unit): JFaces {
    val context = JFacesContext()

    builder.invoke(context)

    return context.faces
}