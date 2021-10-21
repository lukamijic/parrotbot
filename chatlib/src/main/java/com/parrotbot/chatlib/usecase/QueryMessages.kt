package com.parrotbot.chatlib.usecase

import com.parrotbot.chatlib.source.ChatSource

class QueryMessages(private val chatSource: ChatSource) {

    operator fun invoke() = chatSource.messages()
}
