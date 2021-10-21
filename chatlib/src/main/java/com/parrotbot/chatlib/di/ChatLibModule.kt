package com.parrotbot.chatlib.di

import androidx.room.Room
import com.parrotbot.chatlib.source.ChatSource
import com.parrotbot.chatlib.source.ChatSourceImpl
import com.parrotbot.chatlib.source.db.MessageDatabase
import com.parrotbot.chatlib.source.db.mapper.MessageDbMapper
import com.parrotbot.chatlib.source.db.mapper.MessageDbMapperImpl
import com.parrotbot.chatlib.usecase.QueryMessages
import com.parrotbot.chatlib.usecase.SendMessage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

fun chatLibModule(): Module = module {

    single { Room.databaseBuilder(androidContext(), MessageDatabase::class.java, MessageDatabase.NAME).build() }

    single { get<MessageDatabase>().messageDao() }

    single<MessageDbMapper> { MessageDbMapperImpl() }

    single<ChatSource> {
        ChatSourceImpl(
            messageDao = get(),
            dbMapper = get()
        )
    }

    single { QueryMessages(get()) }

    single { SendMessage(get()) }
}
