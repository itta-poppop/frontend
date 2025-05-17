package com.ita.poppop.util

import android.content.Context

import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt

/**
 * 상태바 높이와 동일한 공간을 차지하는 커스텀 뷰
 * 레이아웃에서 상태바 크기만큼의 여백이 필요할 때 사용
 * - android:background 속성으로 배경색 지정 가능
 */
class StatusBarSpaceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val statusBarHeight: Int by lazy {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            // 기본값으로 24dp (대부분의 기기에서 적용되는 일반적인 높이)
            val scale = context.resources.displayMetrics.density
            (24 * scale + 0.5f).toInt()
        }
    }

    init {
        // View 클래스는 이미 background 속성을 처리하므로
        // 추가 코드가 필요하지 않지만, 명시적으로 보여주기 위해 작성

        // XML에서 설정된 속성 값들을 얻기
        if (attrs != null) {
            // android:background는 View 클래스에서 자동으로 처리됨
            // 필요시 기본 배경색 설정
            if (background == null) {
                setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width, statusBarHeight)
    }
}