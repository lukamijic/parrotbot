package com.parrotbot.chat.ui.adapter

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.parrotbot.chat.R

class MessageItemOffsetDecoration(resources: Resources) : RecyclerView.ItemDecoration() {

    private val sameTypeMargin by lazy { resources.getDimensionPixelOffset(R.dimen.item_same_type_margin) }
    private val differentTypeMargin by lazy { resources.getDimensionPixelOffset(R.dimen.item_different_type_margin) }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position != 0) {
            val previousItemType = parent.adapter?.getItemViewType(position - 1)
            val itemType = parent.adapter?.getItemViewType(position)

            when (previousItemType) {
                itemType -> outRect.apply { set(left, top, right, sameTypeMargin) }
                else -> outRect.apply { set(left, top, right, differentTypeMargin) }
            }
        }
    }
}
