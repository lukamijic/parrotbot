package com.parrotbot.chat.ui

import android.os.Bundle
import com.parrotbot.chat.R
import com.parrotbot.chat.databinding.FragmentChatBinding
import com.parrotbot.chat.ui.adapter.MessageItemOffsetDecoration
import com.parrotbot.chat.ui.adapter.MessagesAdapter
import com.parrotbot.commonui.adapter.FirstItemOffsetDecoration
import com.parrotbot.commonui.adapter.LastItemOffsetDecoration
import com.parrotbot.coreui.BaseFragment
import com.parrotbot.coreui.ext.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<ChatViewState, FragmentChatBinding>(FragmentChatBinding::inflate) {

    override val model: ChatViewModel by viewModel()

    private val messagesAdapter by lazy { MessagesAdapter(layoutInflater) }

    private val firstItemTopMargin by lazy { resources.getDimensionPixelOffset(R.dimen.item_first_message_margin_top) }
    private val lastItemMarginBottom by lazy { resources.getDimensionPixelOffset(R.dimen.item_last_message_margin_bottom) }

    override fun FragmentChatBinding.initializeView(savedInstanceState: Bundle?) {
        inputContainer.send.onClick {  }

        initRecyclerView()
    }

    override fun FragmentChatBinding.render(viewState: ChatViewState) {
        messagesAdapter.submitList(viewState.messages)
    }

    private fun FragmentChatBinding.initRecyclerView() {
        recyclerView.apply {
            adapter = messagesAdapter

            /** bottomOffset becomes topMargin because reverseLayout is set to true */
            addItemDecoration(FirstItemOffsetDecoration(bottomOffset = firstItemTopMargin))
            addItemDecoration(LastItemOffsetDecoration(topOffset = lastItemMarginBottom))
            addItemDecoration(MessageItemOffsetDecoration(resources))
        }
    }
}
