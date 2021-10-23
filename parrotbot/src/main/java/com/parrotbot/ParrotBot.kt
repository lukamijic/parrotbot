package com.parrotbot

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.parrotbot.applicationstatelib.di.applicationStateModule
import com.parrotbot.applicationstatelib.source.ApplicationStateSource
import com.parrotbot.chat.di.chatModule
import com.parrotbot.chatlib.di.chatLibModule
import com.parrotbot.core.appconfig.AppConfig
import com.parrotbot.di.parrotBotModule
import com.parrotbot.parrotbotlib.di.parrotBotLibModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class ParrotBot : Application(), KoinComponent {

    private val appConfigs: List<AppConfig> by inject()
    private val applicationStateObserver: ApplicationStateSource by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()

        initLifecycleObservers()
        appConfigs.forEach { it.configure() }
    }

    private fun initLifecycleObservers() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(applicationStateObserver)
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ParrotBot)
            modules(
                listOf(
                    parrotBotModule(),
                    chatModule(),
                    chatLibModule(),
                    parrotBotLibModule(),
                    applicationStateModule()
                )
            )
        }
    }
}
