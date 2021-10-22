package com.parrotbot.chat.ui

import com.parrotbot.chat.ui.adapter.ParrotBotMessage
import com.parrotbot.chat.ui.adapter.UserMessage
import com.parrotbot.chat.ui.mapper.MessagesViewStateMapper
import com.parrotbot.chatlib.model.domain.Sender
import com.parrotbot.chatlib.usecase.QueryMessages
import com.parrotbot.chatlib.usecase.SendMessage
import com.parrotbot.coreui.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ChatViewModel(
    private val sendMessage: SendMessage,
    private val queryMessages: QueryMessages,
    private val messagesViewStateMapper: MessagesViewStateMapper
) : BaseViewModel<ChatViewState>() {

    private val currentMessage = MutableStateFlow("")

    init {
        query {
            queryMessages()
                .map(messagesViewStateMapper::toViewState)
        }

        query {
            currentMessage
                .map { SendButtonEnabled(it.isNotBlank()) }
        }
    }

    fun sendMessage() = runCommand { sendMessage(currentMessage.value.trim(), Sender.USER) }

    fun setMessage(message: String) = runCommand { currentMessage.emit(message) }
}
