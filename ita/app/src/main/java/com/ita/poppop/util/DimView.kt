package com.ita.poppop.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * 전체 화면을 Dim 처리하는 커스텀 뷰
 * 특정 영역만 터치 이벤트를 통과시키고 나머지 영역은 Dim 처리합니다
 */
class DimView(context: Context) : View(context) {

    var onDimAreaClicked: (() -> Unit)? = null

    private val path = Path()
    private val paint = Paint().apply {
        color = Color.parseColor("#88000000") // 반투명 검정색
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    // 터치 이벤트를 통과시킬 영역들
    private var rectAreas = ArrayList<RectF>()
    private var circleAreas = ArrayList<CircleArea>() // 원형 영역을 따로 관리
    private var roundRectAreas = ArrayList<RoundRectArea>() // 둥근 사각형 영역을 따로 관리

    // 원형 영역을 표현하는 데이터 클래스
    data class CircleArea(val centerX: Float, val centerY: Float, val radius: Float) {
        fun contains(x: Float, y: Float): Boolean {
            val distance = sqrt((x - centerX).pow(2) + (y - centerY).pow(2))
            return distance <= radius
        }
    }

    // 둥근 사각형 영역을 표현하는 데이터 클래스
    data class RoundRectArea(val rectF: RectF, val cornerRadius: Float)

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    /**
     * 사각형 터치 이벤트를 통과시킬 영역 추가
     */
    fun addTouchableArea(rectF: RectF) {
        rectAreas.add(rectF)
        invalidate()
    }

    /**
     * 모든 터치 이벤트 통과 영역 초기화
     */
    fun clearTouchableAreas() {
        rectAreas.clear()
        circleAreas.clear()
        roundRectAreas.clear()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 전체 화면을 Dim 처리
        path.reset()
        path.addRect(0f, 0f, width.toFloat(), height.toFloat(), Path.Direction.CW)

        // 사각형 영역에 구멍 뚫기
        for (area in rectAreas) {
            path.addRect(area, Path.Direction.CCW)
        }

        // 원형 영역에 구멍 뚫기
        Log.d("checkDim","DimView onDraw[ circleAreas : ${circleAreas}]")
        for (circle in circleAreas) {
            path.addCircle(circle.centerX, circle.centerY, circle.radius, Path.Direction.CCW)
        }
        // 둥근 사각형 영역에 구멍 뚫기
        for (roundRect in roundRectAreas) {
            path.addRoundRect(roundRect.rectF, roundRect.cornerRadius, roundRect.cornerRadius, Path.Direction.CCW)
        }
        canvas.drawPath(path, paint)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        // 터치 좌표 가져오기
        val touchX = event.x
        val touchY = event.y

        // 사각형 영역 터치 확인
        for (area in rectAreas) {
            if (area.contains(touchX, touchY)) {
                // 특정 영역 내부를 터치한 경우 이벤트 통과
                return false
            }
        }

        // 원형 영역 터치 확인
        for (circle in circleAreas) {
            if (circle.contains(touchX, touchY)) {
                // 원형 영역 내부를 터치한 경우 이벤트 통과
                return false
            }
        }

        // 둥근 사각형 영역 터치 확인 (간단한 사각형 검사만 수행)
        for (roundRect in roundRectAreas) {
            if (roundRect.rectF.contains(touchX, touchY)) {
                // 둥근 사각형 영역 내부를 터치한 경우 이벤트 통과
                return false
            }
        }

        // 딤 영역 클릭 시 콜백 실행
        if (event.action == MotionEvent.ACTION_DOWN) {
            onDimAreaClicked?.invoke()
        }
        return true
    }

    /**
     * 원형 영역 추가 - 실제 원형 모양으로 구멍을 뚫음
     */
    fun addCircleTouchableArea(centerX: Float, centerY: Float, radius: Float) {
        Log.d("checkDim","DimView addCircleTouchableArea[ centerX : ${centerX},centerY : ${centerY},radius : ${radius} ]")
        circleAreas.add(CircleArea(centerX, centerY, radius))
        invalidate()
    }

    /**
     * 둥근 사각형 영역 추가
     */
    fun addRoundRectTouchableArea(rectF: RectF, cornerRadius: Float) {
        roundRectAreas.add(RoundRectArea(rectF, cornerRadius))
        invalidate()
    }

}