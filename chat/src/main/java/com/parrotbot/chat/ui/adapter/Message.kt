package com.parrotbot.chat.ui.adapter

import com.parrotbot.coreui.DiffUtilViewModel

sealed class Message(id: Int, val message: String) : DiffUtilViewModel(id)
class ParrotBotMessage(id: Int, message: String) : Message(id, message)
class UserMessage(id: Int, message: String) : Message(id, message)
