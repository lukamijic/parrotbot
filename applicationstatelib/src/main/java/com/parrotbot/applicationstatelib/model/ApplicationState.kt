package com.parrotbot.applicationstatelib.model

enum class ApplicationState {
    BACKGROUND, FOREGROUND;

    val isBackground: Boolean
        get() = this == BACKGROUND

    val isForeground: Boolean
        get() = this == FOREGROUND
}
