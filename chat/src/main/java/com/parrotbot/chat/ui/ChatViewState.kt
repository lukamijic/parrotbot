package com.parrotbot.chat.ui

import com.parrotbot.chat.ui.adapter.Message

sealed class ChatViewState
data class Messages(val messages: List<Message>) : ChatViewState()
data class SendButtonEnabled(val isEnabled: Boolean) : ChatViewState()
