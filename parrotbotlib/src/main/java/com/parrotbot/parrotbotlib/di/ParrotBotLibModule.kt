package com.parrotbot.parrotbotlib.di

import com.parrotbot.parrotbotlib.notification.ParrotBotMessageNotificationFactory
import com.parrotbot.parrotbotlib.notification.ParrotBotMessageNotificationFactoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PARROT_BOT_RESPONSE_PENDING_INTENT = "PARROT_BOT_RESPONSE_PENDING_INTENT"

fun parrotBotLibModule() : Module = module {

    single<ParrotBotMessageNotificationFactory> {
        ParrotBotMessageNotificationFactoryImpl(
            androidContext(),
            get(named(PARROT_BOT_RESPONSE_PENDING_INTENT)),
            androidContext().resources
        )
    }
}
