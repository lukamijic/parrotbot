package com.parrotbot.parrotbotlib.worker

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.parrotbot.applicationstatelib.usecase.GetApplicationState
import com.parrotbot.chatlib.model.domain.Sender
import com.parrotbot.chatlib.usecase.SendMessage
import com.parrotbot.parrotbotlib.notification.ParrotBotMessageNotificationFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ParrotBotResponseWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    companion object {

        const val MESSAGE_KEY = "MESSAGE_KEY"
    }

    private val sendMessage: SendMessage by inject()
    private val getApplicationState: GetApplicationState by inject()
    private val parrotBotMessageNotificationFactory: ParrotBotMessageNotificationFactory by inject()

    override suspend fun doWork(): Result {
        val parrotMessage = inputData.getString(MESSAGE_KEY)!!.mockify()
        sendMessage(parrotMessage, Sender.PARROT_BOT)

        if (getApplicationState().isBackground) {
            showNotification(parrotMessage)
        }
        return Result.success()
    }

    private fun showNotification(message: String) {
        NotificationManagerCompat.from(applicationContext).notify(
            0,
            parrotBotMessageNotificationFactory.createNotification(message)
        )
    }

    private fun String.mockify() = StringBuilder().also {
        var toUpper = true
        for (c in this) {
            val mockified = when {
                c.isLetter() -> {
                    if (toUpper.also { toUpper = toUpper.not() }) c.uppercaseChar() else c.lowercaseChar()
                }
                else -> c
            }

            it.append(mockified)
        }
    }.toString()
}
