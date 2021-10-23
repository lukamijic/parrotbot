package com.parrotbot.applicationstatelib.source

import androidx.lifecycle.LifecycleObserver
import com.parrotbot.applicationstatelib.model.ApplicationState

interface ApplicationStateSource : LifecycleObserver {

    suspend fun applicationState(): ApplicationState
}
