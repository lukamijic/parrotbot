package com.parrotbot.appconfig

import com.parrotbot.core.appconfig.AppConfig
import timber.log.Timber

class TimberAppConfig : AppConfig {

    override fun configure() {
        Timber.plant(Timber.DebugTree())
    }
}
