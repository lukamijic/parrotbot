package com.parrotbot.chatlib.source.db.mapper

import com.parrotbot.chatlib.model.db.DbMessage
import com.parrotbot.chatlib.model.db.DbSender
import com.parrotbot.chatlib.model.domain.Message
import com.parrotbot.chatlib.model.domain.Sender

interface MessageDbMapper {

    fun toMessages(dbMessages: List<DbMessage>) : List<Message>

    fun toMessage(dbMessage: DbMessage) : Message

    fun toDbMessage(message: Message) : DbMessage

    fun toDbMessage(message: String, timeStamp: Long, sender: Sender) : DbMessage

    fun toSender(dbSender: DbSender) : Sender

    fun toDbSender(sender: Sender) : DbSender
}
