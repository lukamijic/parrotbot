package com.parrotbot.chatlib.source.db.mapper

import com.parrotbot.chatlib.model.db.DbMessage
import com.parrotbot.chatlib.model.db.DbSender
import com.parrotbot.chatlib.model.domain.Message
import com.parrotbot.chatlib.model.domain.Sender


class MessageDbMapperImpl : MessageDbMapper {

    override fun toMessages(dbMessages: List<DbMessage>) = dbMessages.map { toMessage(it) }

    override fun toMessage(dbMessage: DbMessage): Message = with(dbMessage) {
        Message(
            id,
            message,
            timeStamp,
            toSender(sender)
        )
    }

    override fun toDbMessage(message: Message): DbMessage = with(message) {
        DbMessage(
            id,
            this.message,
            timestamp,
            toDbSender(sender)
        )
    }

    override fun toDbMessage(message: String, timeStamp: Long, sender: Sender) =
        DbMessage(
            message = message,
            timeStamp = timeStamp,
            sender = toDbSender(sender)
        )

    override fun toSender(dbSender: DbSender): Sender = when (dbSender) {
        DbSender.USER -> Sender.USER
        DbSender.PARROT_BOT -> Sender.PARROT_BOT
    }

    override fun toDbSender(sender: Sender): DbSender = when (sender) {
        Sender.USER -> DbSender.USER
        Sender.PARROT_BOT -> DbSender.PARROT_BOT
    }
}
