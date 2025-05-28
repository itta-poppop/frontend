package com.ita.poppop.view.main.home.upcoming

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HomeUpcomingItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val SPACING_UNIT = 3.0f
        private const val TOP_SPACING_DP = 8
        private const val SIDE_SPACING_DP = 6
        private const val COLUMN_COUNT = 2
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = (position % COLUMN_COUNT) + 1

        // 상단 여백 (첫 번째 행 제외)
        if (position >= COLUMN_COUNT) {
            outRect.top = (TOP_SPACING_DP * SPACING_UNIT).toInt()
        }

        // 좌측 여백 (첫 번째 열 제외)
        if (column != 1) {
            outRect.left = (SIDE_SPACING_DP * SPACING_UNIT).toInt()
        }

        // 하단 여백은 항상
        outRect.bottom = (TOP_SPACING_DP * SPACING_UNIT).toInt()

        // 우측 여백 (짝수 번째 아이템만)
        if (position % COLUMN_COUNT == 0) {
            outRect.right = (SIDE_SPACING_DP * SPACING_UNIT).toInt()
        }
    }
}
