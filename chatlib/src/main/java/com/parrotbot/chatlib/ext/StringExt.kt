package com.parrotbot.chatlib.ext

fun String.mockify() = StringBuilder().also {
    var toUpper = true
    for (c in this) {
        val mockified = when {
            c.isLetter() -> {
                if (toUpper.also { toUpper = toUpper.not() }) c.uppercaseChar() else c.lowercaseChar()
            }
            else -> c
        }

        it.append(mockified)
    }
}.toString()
