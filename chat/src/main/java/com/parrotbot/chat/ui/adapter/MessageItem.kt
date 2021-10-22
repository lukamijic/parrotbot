package com.parrotbot.chat.ui.adapter

import com.parrotbot.coreui.DiffUtilViewModel

sealed class MessageItem(id: Any) : DiffUtilViewModel(id)
class ParrotBotMessage(id: Int, val message: String) : MessageItem(id)
class UserMessage(id: Int, val message: String) : MessageItem(id)
class MessageTimestamp(id: String, val date: String, val hourMinutes: String): MessageItem(id)
