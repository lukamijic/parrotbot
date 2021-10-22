package com.parrotbot.chat.di

import com.parrotbot.chat.ui.ChatViewModel
import com.parrotbot.chat.ui.mapper.MessagesViewStateMapper
import com.parrotbot.chat.ui.mapper.MessagesViewStateMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun chatModule(): Module = module {

    viewModel {
        ChatViewModel(
            sendMessage = get(),
            queryMessages = get(),
            messagesViewStateMapper = get()
        )
    }

    single<MessagesViewStateMapper> { MessagesViewStateMapperImpl() }
}
