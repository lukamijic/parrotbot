package com.parrotbot.commonui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

private const val NO_OFFSET = 0

class LastItemOffsetDecoration(
    private val leftOffset: Int = NO_OFFSET,
    private val topOffset: Int = NO_OFFSET,
    private val rightOffset: Int = NO_OFFSET,
    private val bottomOffset: Int = NO_OFFSET
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
            outRect.set(leftOffset, topOffset, rightOffset, bottomOffset)
        }
    }
}
