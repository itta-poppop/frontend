package com.ita.poppop.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class StatusBarSpaceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        // 초기 높이는 0으로 설정 (나중에 WindowInsets로 갱신)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)

        // WindowInsets 적용
        ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            layoutParams.height = statusBarHeight
            requestLayout()
            insets
        }

        ViewCompat.requestApplyInsets(this)
    }
}
