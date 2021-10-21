package com.parrotbot.chatlib.source

import com.parrotbot.chatlib.model.domain.Message
import com.parrotbot.chatlib.model.domain.Sender
import kotlinx.coroutines.flow.Flow

interface ChatSource {

    suspend fun sendMessage(message: String, sender: Sender)

    fun messages() : Flow<List<Message>>
}
