package com.parrotbot.chat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.parrotbot.chat.databinding.ItemParrotbotMessageBinding
import com.parrotbot.chat.databinding.ItemUserMessageBinding
import com.parrotbot.coreui.BaseListAdapter
import com.parrotbot.coreui.BaseViewHolder

private const val USER_MESSAGE = 0
private const val PARROTBOT_MESSAGE = 1

class MessagesAdapter(private val layoutInflater: LayoutInflater) : BaseListAdapter<Message, BaseViewHolder<Message, ViewBinding>>() {

    override fun getItemViewType(position: Int): Int = when(currentList[position]) {
        is UserMessage -> USER_MESSAGE
        is ParrotBotMessage -> PARROTBOT_MESSAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Message, ViewBinding> =
        when(viewType) {
            USER_MESSAGE -> UserMessageViewHolder(ItemUserMessageBinding.inflate(layoutInflater, parent, false))
            PARROTBOT_MESSAGE -> ParrotBotMessageViewHolder(ItemParrotbotMessageBinding.inflate(layoutInflater, parent, false))
            else -> throw IllegalArgumentException("Invalid viewType passed : $viewType")
        } as BaseViewHolder<Message, ViewBinding>

    override fun onBindViewHolder(holder: BaseViewHolder<Message, ViewBinding>, position: Int) =
        when (val item = getItem(position)) {
            is UserMessage -> (holder as UserMessageViewHolder).render(item)
            is ParrotBotMessage -> (holder as ParrotBotMessageViewHolder).render(item)
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
}
