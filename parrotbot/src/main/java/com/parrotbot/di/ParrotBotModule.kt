package com.parrotbot.di

import android.app.PendingIntent
import android.content.Intent
import androidx.work.WorkManager
import com.parrotbot.activity.ParrotBotActivity
import com.parrotbot.appconfig.NotificationAppConfig
import com.parrotbot.appconfig.TimberAppConfig
import com.parrotbot.parrotbotlib.di.PARROT_BOT_RESPONSE_PENDING_INTENT
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun parrotBotModule() : Module = module {

    single { WorkManager.getInstance(androidContext()) }

    single {
        listOf(
            TimberAppConfig(),
            NotificationAppConfig(
                context = androidContext(),
                parrotBotMessageNotificationFactory = get()
            )
        )
    }

    single<PendingIntent>(named(PARROT_BOT_RESPONSE_PENDING_INTENT)) {
        PendingIntent.getActivity(
            androidContext(),
            0,
            Intent(androidContext(), ParrotBotActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            },
            0
        )
    }
}
