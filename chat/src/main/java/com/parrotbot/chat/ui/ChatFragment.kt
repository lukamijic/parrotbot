package com.parrotbot.chat.ui

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.parrotbot.chat.R
import com.parrotbot.chat.databinding.FragmentChatBinding
import com.parrotbot.chat.ui.adapter.MessageItemOffsetDecoration
import com.parrotbot.chat.ui.adapter.MessagesAdapter
import com.parrotbot.commonui.adapter.FirstItemOffsetDecoration
import com.parrotbot.commonui.adapter.LastItemOffsetDecoration
import com.parrotbot.coreui.BaseFragment
import com.parrotbot.coreui.ext.enable
import com.parrotbot.coreui.ext.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<ChatViewState, FragmentChatBinding>(FragmentChatBinding::inflate) {

    override val model: ChatViewModel by viewModel()

    private val messagesAdapter by lazy { MessagesAdapter(layoutInflater) }

    private val firstItemTopMargin by lazy { resources.getDimensionPixelOffset(R.dimen.item_first_message_margin_top) }
    private val lastItemMarginBottom by lazy { resources.getDimensionPixelOffset(R.dimen.item_last_message_margin_bottom) }

    override fun FragmentChatBinding.initializeView(savedInstanceState: Bundle?) {
        inputContainer.send.onClick {
            model.sendMessage()
            inputContainer.chatBox.text?.clear()
        }
        inputContainer.chatBox.doAfterTextChanged { model.setMessage(it.toString()) }

        initRecyclerView()
    }

    override fun FragmentChatBinding.render(viewState: ChatViewState) = when(viewState) {
        is Messages -> render(viewState)
        is SendButtonEnabled -> render(viewState)
    }

    private fun FragmentChatBinding.render(viewState: Messages) {
        messagesAdapter.submitList(viewState.messages)
    }

    private fun FragmentChatBinding.render(viewState: SendButtonEnabled) {
        inputContainer.send.enable(viewState.isEnabled)
    }


    private fun FragmentChatBinding.initRecyclerView() {
        recyclerView.apply {
            adapter = messagesAdapter
            messagesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    recyclerView.scrollToPosition(messagesAdapter.itemCount - 1)
                }
            })
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

            addItemDecoration(FirstItemOffsetDecoration(topOffset = firstItemTopMargin))
            addItemDecoration(LastItemOffsetDecoration(bottomOffset = lastItemMarginBottom))
            addItemDecoration(MessageItemOffsetDecoration(resources))
        }
    }
}
