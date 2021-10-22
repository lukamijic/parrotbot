package com.parrotbot.chat.ui.mapper

import com.parrotbot.chat.ui.Messages
import com.parrotbot.chatlib.model.domain.Message

interface MessagesViewStateMapper {

    fun toViewState(messages: List<Message>) : Messages
}
