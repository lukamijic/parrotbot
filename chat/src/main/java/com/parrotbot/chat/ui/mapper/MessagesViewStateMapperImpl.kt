package com.parrotbot.chat.ui.mapper

import com.parrotbot.chat.ui.Messages
import com.parrotbot.chat.ui.adapter.MessageItem
import com.parrotbot.chat.ui.adapter.MessageTimestamp
import com.parrotbot.chat.ui.adapter.ParrotBotMessage
import com.parrotbot.chat.ui.adapter.UserMessage
import com.parrotbot.chatlib.model.domain.Message
import com.parrotbot.chatlib.model.domain.Sender
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

private const val DAY_OF_WEEK_FORMAT = "EEEE"
private const val HOURS_MINUTES_FORMAT = "HH:mm"
private const val DATE_FORMAT = "dd.MM.yyyy"

private val ONE_HOUR_IN_MILLIS = TimeUnit.HOURS.toMillis(1)
private val ONE_WEEK_IN_MILLIS = TimeUnit.DAYS.toMillis(7)
private val TWENTY_SECONDS_IN_MILLIS = TimeUnit.SECONDS.toMillis(20)

class MessagesViewStateMapperImpl : MessagesViewStateMapper {

    private val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    private val dayFormatter = SimpleDateFormat(DAY_OF_WEEK_FORMAT, Locale.getDefault())
    private val hoursMinutesFormatter = SimpleDateFormat(HOURS_MINUTES_FORMAT, Locale.getDefault())

    override fun toViewState(messages: List<Message>): Messages {
        val messageViewModels = mutableListOf<MessageItem>()

        messages.forEachIndexed { index, message ->
            if (index == 0 || message.overOneHourTimestampDifference(messages[index - 1])) {
                messageViewModels.add(message.toMessageTimestamp())
            }

            messageViewModels.add(
                message.toMessageItem(
                    message.hasTail(messages.getOrNull(index + 1))
                )
            )
        }

        return Messages(messageViewModels)
    }

    private fun Message.toMessageItem(hasTail: Boolean) = when (sender) {
        Sender.USER -> UserMessage(id, message, hasTail)
        Sender.PARROT_BOT -> ParrotBotMessage(id, message, hasTail)
    }

    private fun Message.hasTail(next: Message?) = when {
        next == null -> true
        sender != next.sender -> true
        else -> abs(timestamp - next.timestamp) >= TWENTY_SECONDS_IN_MILLIS
    }

    private fun Message.toMessageTimestamp() = MessageTimestamp(
        toTimestampId(id),
        if (isOlderThanAWeek()) dateFormatter.format(timestamp) else dayFormatter.format(timestamp),
        hoursMinutesFormatter.format(timestamp)
    )

    private fun Message.overOneHourTimestampDifference(other: Message) =
        abs(timestamp - other.timestamp) > ONE_HOUR_IN_MILLIS

    private fun Message.isOlderThanAWeek() =
        abs(timestamp - System.currentTimeMillis()) > ONE_WEEK_IN_MILLIS

    private fun toTimestampId(messageId: Int) = "$messageId - timestamp"
}
