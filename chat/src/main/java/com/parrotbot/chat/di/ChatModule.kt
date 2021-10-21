package com.parrotbot.chat.di

import com.parrotbot.chat.ui.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun chatModule(): Module = module {

    viewModel { ChatViewModel() }
}
