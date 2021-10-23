package com.parrotbot.parrotbotlib.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.parrotbot.parrotbotlib.R

class ParrotBotMessageNotificationFactoryImpl(
    private val context: Context,
    private val parrotBotNotificationClickedAction: PendingIntent,
    private val resources: Resources
) : ParrotBotMessageNotificationFactory {

    override val channelId: String = "PARROT_BOT_MESSAGE"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun notificationChannel(): NotificationChannel =
        NotificationChannel(channelId, resources.getString(R.string.parrotbot_message_notification_channel_name), NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = resources.getString(R.string.parrotbot_message_notification_channel_description)
        }

    override fun createNotification(message: String): Notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_parrot)
        .setContentTitle(resources.getString(R.string.parrotbot_message_notification_title))
        .setContentText(message)
        .setChannelId(channelId)
        .setAutoCancel(true)
        .setContentIntent(parrotBotNotificationClickedAction)
        .build()

}
