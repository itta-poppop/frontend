package com.ita.poppop.view.main.home.direction

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class HomeDirectionItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = (8 * 3.0f).toInt()  // 오른쪽에만 간격 추가
    }
}
