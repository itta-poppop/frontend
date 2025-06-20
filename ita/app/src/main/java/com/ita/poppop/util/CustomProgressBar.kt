package com.ita.poppop.util

// CustomProgressBar.kt
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class CustomProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "CustomProgressBar"
    }

    private var progress = 0f
    private val maxProgress = 50f
    private var isDragging = false

    // 진행률 변경 리스너
    private var onProgressChangeListener: ((Float) -> Unit)? = null

    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 12f
        strokeCap = Paint.Cap.ROUND
    }

    // 썸 테두리용 Paint
    private val thumbBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 8f
        color = Color.WHITE
    }

    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#9CE97C")
        setShadowLayer(10f, 0f, 4f, Color.parseColor("#40000000"))
    }

    private var gradientShader: LinearGradient? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // 그라데이션 생성 (초록 → 노랑 → 빨강)
        gradientShader = LinearGradient(
            0f, 0f, w.toFloat(), 0f,
            intArrayOf(
                Color.parseColor("#8DF084"), // 초록
                Color.parseColor("#F0C14C"), // 노랑
                Color.parseColor("#D0764D"), // 노랑
                Color.parseColor("#9D0D0C")  // 빨강
            ),
            null,
            Shader.TileMode.CLAMP
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerY = height / 2f
        val startX = 40f
        val endX = width - 40f
        val trackLength = endX - startX

        // 배경 트랙 그리기 (회색)
        trackPaint.color = Color.parseColor("#E0E0E0")
        canvas.drawLine(startX, centerY, endX, centerY, trackPaint)

        // 프로그레스 트랙 그리기 (그라데이션)
        if (progress > 0) {
            val progressX = startX + (trackLength * progress / maxProgress)
            trackPaint.shader = gradientShader
            canvas.drawLine(startX, centerY, progressX, centerY, trackPaint)
            trackPaint.shader = null
        }

        // 썸(원) 그리기
        val thumbX = startX + (trackLength * progress / maxProgress)
        val thumbRadius = 32f

        // 썸 본체
        canvas.drawCircle(thumbX, centerY, thumbRadius, thumbPaint)
        // 썸 테두리
        canvas.drawCircle(thumbX, centerY, thumbRadius, thumbBorderPaint)
    }

    fun setProgress(newProgress: Float, notifyListener: Boolean = true) {
        val oldProgress = progress
        progress = newProgress.coerceIn(0f, maxProgress)

        // 값이 실제로 변경되었고, 리스너 호출이 필요한 경우
        if (notifyListener && oldProgress != progress) {
            Log.d(TAG, "Progress changed from $oldProgress to $progress (${(progress/maxProgress*100).toInt()}%)")
            onProgressChangeListener?.invoke(progress)
        }

        invalidate()
    }

    fun setProgress(newProgress: Float) {
        val oldProgress = progress
        progress = newProgress.coerceIn(0f, maxProgress)

        // 값이 변경된 경우 로그 출력 및 리스너 호출
        if (oldProgress != progress) {
            Log.d(TAG, "Progress set to $progress (${(progress/maxProgress*100).toInt()}%)")
            onProgressChangeListener?.invoke(progress)
        }

        invalidate()
    }

    fun getProgress(): Float = progress

    fun setOnProgressChangeListener(listener: (Float) -> Unit) {
        onProgressChangeListener = listener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val centerY = height / 2f
        val startX = 40f
        val endX = width - 40f
        val trackLength = endX - startX

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val touchX = event.x
                val touchY = event.y
                val currentThumbX = startX + (trackLength * progress / maxProgress)

                // 썸 영역 또는 트랙 영역 터치 확인
                val thumbRadius = 24f
                val distanceFromThumb = kotlin.math.sqrt(
                    (touchX - currentThumbX) * (touchX - currentThumbX) +
                            (touchY - centerY) * (touchY - centerY)
                ).toFloat()

                if (distanceFromThumb <= thumbRadius + 20f ||
                    (touchX >= startX && touchX <= endX &&
                            kotlin.math.abs(touchY - centerY) <= 30f)) {
                    isDragging = true
                    Log.d(TAG, "Touch started - begin dragging")
                    updateProgressFromTouch(touchX, startX, trackLength)
                    parent.requestDisallowInterceptTouchEvent(true)
                    return true
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (isDragging) {
                    updateProgressFromTouch(event.x, startX, trackLength)
                    return true
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (isDragging) {
                    isDragging = false
                    Log.d(TAG, "Touch ended - final progress: $progress (${(progress/maxProgress*100).toInt()}%)")
                    parent.requestDisallowInterceptTouchEvent(false)
                    return true
                }
            }
        }

        return super.onTouchEvent(event)
    }

    private fun updateProgressFromTouch(touchX: Float, startX: Float, trackLength: Float) {
        val newProgress = ((touchX - startX) / trackLength * maxProgress).coerceIn(0f, maxProgress)
        if (newProgress != progress) {
            val oldProgress = progress
            progress = newProgress
            Log.d(TAG, "Touch drag - progress changed from $oldProgress to $progress (${(progress/maxProgress*100).toInt()}%)")
            onProgressChangeListener?.invoke(progress)
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val shadowBuffer = 20 // 그림자가 퍼질 여유 공간
        val desiredWidth = 400 + shadowBuffer * 2
        val desiredHeight = 80 + shadowBuffer * 2

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> kotlin.math.min(desiredWidth, widthSize)
            else -> desiredWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> kotlin.math.min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }
}