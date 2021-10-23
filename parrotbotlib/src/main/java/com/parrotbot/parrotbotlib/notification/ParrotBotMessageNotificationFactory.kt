package com.parrotbot.parrotbotlib.notification

import android.app.Notification
import android.app.NotificationChannel
import android.os.Build
import androidx.annotation.RequiresApi

interface ParrotBotMessageNotificationFactory {

    val channelId: String

    @RequiresApi(Build.VERSION_CODES.O)
    fun notificationChannel(): NotificationChannel

    fun createNotification(message: String): Notification
}
