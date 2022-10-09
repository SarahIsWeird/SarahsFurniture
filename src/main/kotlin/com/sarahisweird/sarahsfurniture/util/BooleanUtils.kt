package com.sarahisweird.sarahsfurniture.util

fun Boolean?.isTrueOrNull() =
    this != false

fun Boolean?.isFalseOrNull() =
    this != true