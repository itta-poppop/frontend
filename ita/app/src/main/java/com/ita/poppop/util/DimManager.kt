package com.ita.poppop.util


import android.app.Activity
import android.graphics.RectF
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window

/**
 * 전체 화면 Dim 처리를 관리하는 클래스
 */
class DimManager(private val activity: Activity) {
    private var dimView: DimView? = null
    private var isDimmed = false

    init {
        createDimView()
    }

    private fun createDimView() {
        dimView = DimView(activity)
    }

    /**
     * 화면 Dim 처리
     */
    fun showDim() {
        if (isDimmed) return

        val window = activity.window
        val decorView = window.decorView as ViewGroup

        dimView?.let {
            decorView.addView(it)
            isDimmed = true
        }
    }

    /**
     * Dim 처리 해제
     */
    fun hideDim() {
        if (!isDimmed) return

        val window = activity.window
        val decorView = window.decorView as ViewGroup

        dimView?.let {
            decorView.removeView(it)
            isDimmed = false
        }
    }

    /**
     * Dim 상태 토글
     */
    fun toggleDim() {
        if (isDimmed) {
            hideDim()
        } else {
            showDim()
        }
    }

    /**
     * 특정 뷰의 영역을 터치 가능 영역으로 추가
     */
    fun addTouchableView(view: View) {
        val location = IntArray(2)
        view.getLocationInWindow(location)

        val rectF = RectF(
            location[0].toFloat(),
            location[1].toFloat(),
            (location[0] + view.width).toFloat(),
            (location[1] + view.height).toFloat()
        )

        dimView?.addTouchableArea(rectF)
    }

    /**
     * 특정 좌표와 크기로 터치 가능 영역 추가
     */
    fun addTouchableArea(rectF: RectF) {
        dimView?.addTouchableArea(rectF)
    }

    /**
     * 원형 터치 가능 영역 추가
     */
    fun addCircleTouchableArea(centerX: Float, centerY: Float, radius: Float) {
        Log.d("checkDim","DimManager addCircleTouchableArea[ centerX : ${centerX},centerY : ${centerY},radius : ${radius} ]")
        dimView?.addCircleTouchableArea(centerX, centerY, radius)
    }

    /**
     * 둥근 사각형 터치 가능 영역 추가
     */
    fun addRoundRectTouchableArea(rectF: RectF, cornerRadius: Float) {
        dimView?.addRoundRectTouchableArea(rectF, cornerRadius)
    }

    /**
     * 모든 터치 가능 영역 초기화
     */
    fun clearTouchableAreas() {
        dimView?.clearTouchableAreas()
    }

    /**
     * 리소스 정리
     */
    fun release() {
        hideDim()
        dimView = null
    }
}