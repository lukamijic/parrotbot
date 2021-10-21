package com.parrotbot.chatlib.source

import com.parrotbot.chatlib.ext.mockify
import com.parrotbot.chatlib.model.domain.Message
import com.parrotbot.chatlib.model.domain.Sender
import com.parrotbot.chatlib.source.db.MessageDao
import com.parrotbot.chatlib.source.db.mapper.MessageDbMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatSourceImpl(
    private val messageDao: MessageDao,
    private val dbMapper: MessageDbMapper
) : ChatSource {

    override suspend fun sendMessage(message: String, sender: Sender) {
        messageDao.send(dbMapper.toDbMessage(message, System.currentTimeMillis(), sender))

        delay(2000)

        messageDao.send(dbMapper.toDbMessage(message.mockify(), System.currentTimeMillis(), Sender.PARROT_BOT))
    }

    override fun messages(): Flow<List<Message>> = messageDao.messages().map(dbMapper::toMessages)
}
