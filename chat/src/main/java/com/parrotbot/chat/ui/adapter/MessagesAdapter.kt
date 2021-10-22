package com.parrotbot.chat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.viewbinding.ViewBinding
import com.parrotbot.chat.R
import com.parrotbot.chat.databinding.ItemMessageTimestampBinding
import com.parrotbot.chat.databinding.ItemParrotbotMessageBinding
import com.parrotbot.chat.databinding.ItemUserMessageBinding
import com.parrotbot.commonui.span.CustomTypefaceSpan
import com.parrotbot.coreui.BaseListAdapter
import com.parrotbot.coreui.BaseViewHolder

private const val USER_MESSAGE = 0
private const val PARROTBOT_MESSAGE = 1
private const val MESSAGE_TIMESTAMP = 2

class MessagesAdapter(private val layoutInflater: LayoutInflater) : BaseListAdapter<MessageItem, BaseViewHolder<MessageItem, ViewBinding>>() {

    override fun getItemViewType(position: Int): Int = when(currentList[position]) {
        is UserMessage -> USER_MESSAGE
        is ParrotBotMessage -> PARROTBOT_MESSAGE
        is MessageTimestamp -> MESSAGE_TIMESTAMP
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MessageItem, ViewBinding> =
        when(viewType) {
            USER_MESSAGE -> UserMessageViewHolder(ItemUserMessageBinding.inflate(layoutInflater, parent, false))
            PARROTBOT_MESSAGE -> ParrotBotMessageViewHolder(ItemParrotbotMessageBinding.inflate(layoutInflater, parent, false))
            MESSAGE_TIMESTAMP -> MessageTimestampViewHolder(ItemMessageTimestampBinding.inflate(layoutInflater, parent, false))
            else -> throw IllegalArgumentException("Invalid viewType passed : $viewType")
        } as BaseViewHolder<MessageItem, ViewBinding>

    override fun onBindViewHolder(holder: BaseViewHolder<MessageItem, ViewBinding>, position: Int) =
        when (val item = getItem(position)) {
            is UserMessage -> (holder as UserMessageViewHolder).render(item)
            is ParrotBotMessage -> (holder as ParrotBotMessageViewHolder).render(item)
            is MessageTimestamp -> (holder as MessageTimestampViewHolder).render(item)
        }

    class UserMessageViewHolder(
        binding: ItemUserMessageBinding
    ) : BaseViewHolder<UserMessage, ItemUserMessageBinding>(binding) {

        override fun ItemUserMessageBinding.render(item: UserMessage) {
            message.text = item.message
        }
    }

    class ParrotBotMessageViewHolder(
        binding: ItemParrotbotMessageBinding
    ) : BaseViewHolder<ParrotBotMessage, ItemParrotbotMessageBinding>(binding) {

        override fun ItemParrotbotMessageBinding.render(item: ParrotBotMessage) {
            message.text = item.message
        }
    }

    class MessageTimestampViewHolder(
        binding: ItemMessageTimestampBinding
    ) : BaseViewHolder<MessageTimestamp, ItemMessageTimestampBinding>(binding) {

        private val boldTextFont by lazy { ResourcesCompat.getFont(itemView.context, R.font.roboto_bold) }

        override fun ItemMessageTimestampBinding.render(item: MessageTimestamp) {
            timestamp.text = buildSpannedString {
                inSpans(CustomTypefaceSpan(boldTextFont)) {
                    append(item.date)
                }
                append(" ")
                append(item.hourMinutes)
            }
        }
    }
}
