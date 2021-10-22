package com.parrotbot.chat.ui

import com.parrotbot.chat.ui.adapter.MessageItem

sealed class ChatViewState
data class Messages(val messageItems: List<MessageItem>) : ChatViewState()
data class SendButtonEnabled(val isEnabled: Boolean) : ChatViewState()
