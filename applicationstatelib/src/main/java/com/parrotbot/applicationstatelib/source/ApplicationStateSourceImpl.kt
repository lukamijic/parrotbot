package com.parrotbot.applicationstatelib.source

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.parrotbot.applicationstatelib.model.ApplicationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ApplicationStateSourceImpl : ApplicationStateSource {

    private val bgScope = CoroutineScope(Dispatchers.Default)
    private val applicationState = MutableStateFlow(ApplicationState.BACKGROUND)

    override suspend fun applicationState(): ApplicationState = applicationState.first()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() = bgScope.launch {
        applicationState.emit(ApplicationState.FOREGROUND)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() = bgScope.launch {
        applicationState.emit(ApplicationState.BACKGROUND)
    }
}
