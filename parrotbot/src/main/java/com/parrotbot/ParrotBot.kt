package com.parrotbot

import android.app.Application
import com.parrotbot.appconfig.TimberAppConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ParrotBot : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()

        TimberAppConfig().configure()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ParrotBot)
            modules(
                listOf(

                )
            )
        }
    }
}
