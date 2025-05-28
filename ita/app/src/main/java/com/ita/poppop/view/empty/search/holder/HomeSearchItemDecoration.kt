package com.ita.poppop.view.empty.search.holder

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class HomeSearchItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val SPACING_UNIT = 3.0f
        private const val TOP_SPACING_DP = 16
        private const val SIDE_SPACING_DP = 6
        private const val COLUMN_COUNT = 2
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val columnIndex = (position % COLUMN_COUNT) + 1  // 1부터 시작하는 열 번호

        val topSpacing = (TOP_SPACING_DP * SPACING_UNIT).toInt()
        val sideSpacing = (SIDE_SPACING_DP * SPACING_UNIT).toInt()

        // 첫 번째 행은 위 여백 없음
        if (position >= COLUMN_COUNT) {
            outRect.top = topSpacing
        }

        // 첫 번째 열이 아닌 경우 좌측 여백 추가
        if (columnIndex != 1) {
            outRect.left = sideSpacing
        }

        // 하단 여백은 항상 추가
        outRect.bottom = topSpacing

        // 짝수 번째 아이템(왼쪽 열)에만 우측 여백 추가
        if (position % COLUMN_COUNT == 0) {
            outRect.right = sideSpacing
        }
    }
}
