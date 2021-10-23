package com.parrotbot.parrotbotlib.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.parrotbot.chatlib.model.domain.Message
import com.parrotbot.chatlib.model.domain.Sender
import com.parrotbot.chatlib.usecase.QueryMessages
import com.parrotbot.parrotbotlib.worker.ParrotBotResponseWorker
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

private const val PARROT_BOT_DELAY_SECONDS = 10L

class ParrotBotService : Service(), KoinComponent {

    private val serviceScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val queryMessages: QueryMessages by inject()
    private val workManager: WorkManager by inject()

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        serviceScope.launch {
            queryMessages()
                .drop(1)
                .filter(List<Message>::isNotEmpty)
                .map(List<Message>::last)
                .filter { it.sender == Sender.USER }
                .distinctUntilChangedBy { it.id }
                .collect { workManager.enqueue(parrotResponseRequest(it.message)) }
        }
    }

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }

    private fun parrotResponseRequest(message: String) =
        OneTimeWorkRequestBuilder<ParrotBotResponseWorker>()
            .setInputData(workDataOf(ParrotBotResponseWorker.MESSAGE_KEY to message))
            .setInitialDelay(PARROT_BOT_DELAY_SECONDS, TimeUnit.SECONDS)
            .build()
}
