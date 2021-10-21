package com.parrotbot.chatlib.usecase

import com.parrotbot.chatlib.model.domain.Sender
import com.parrotbot.chatlib.source.ChatSource

class SendMessage(private val chatSource: ChatSource) {

    suspend operator fun invoke(message: String, sender: Sender) = chatSource.sendMessage(message, sender)
}
