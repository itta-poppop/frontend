package com.ita.poppop.view.main.home.trend

import android.graphics.Rect
import android.content.Context
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView

class HomeTrendItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = (10 * 3.0f).toInt()  // 오른쪽에만 간격 추가
    }
}
