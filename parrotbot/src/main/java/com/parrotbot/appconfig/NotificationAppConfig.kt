package com.parrotbot.appconfig

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.parrotbot.core.appconfig.AppConfig
import com.parrotbot.parrotbotlib.notification.ParrotBotMessageNotificationFactory

class NotificationAppConfig(
    private val context: Context,
    private val parrotBotMessageNotificationFactory: ParrotBotMessageNotificationFactory
) : AppConfig {

    override fun configure() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
                createNotificationChannel(parrotBotMessageNotificationFactory.notificationChannel())
            }
        }
    }
}
