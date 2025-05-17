package com.ita.poppop.util

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.R
import com.ita.poppop.view.empty.notification.holder.HomeNotificationViewHolder
import kotlin.math.max
import kotlin.math.min

class SwipeHelper: ItemTouchHelper.Callback() {

    private var currentPosition: Int? = null
    private var previousPosition: Int? = null
    private var currentDx = 0f
    private var clamp = 0f

    // ViewHolder의 포지션을 키로 사용하여 clamped 상태를 저장하는 맵
    private val clampedMap = mutableMapOf<Int, Boolean>()

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        clamp = recyclerView.context.resources.getDimensionPixelSize(R.dimen.item_54).toFloat()
                // Drag는 사용하지 않고(0), Swipe는 LEFT, RIGHT 양방향 허용
        return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // 스와이프 완료 후 호출되지만, 여기서는 사용하지 않음
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        currentDx = 0f
        getDefaultUIUtil().clearView(getView(viewHolder))
        previousPosition = viewHolder.bindingAdapterPosition
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let {
            currentPosition = viewHolder.bindingAdapterPosition  // 현재 스와이프 한 아이템 위치
            getDefaultUIUtil().onSelected(getView(it))
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            val view = getView(viewHolder)
            val isClamped = getIsClamped(viewHolder.bindingAdapterPosition)

            val x = clampViewPositionHorizontal(view, dX, isClamped, isCurrentlyActive)
            currentDx = x

            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                view,
                x,  // 수정: dX 대신 계산된 x 값 사용
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }

    private fun clampViewPositionHorizontal(
        view: View,
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean
    ) : Float {
        // View의 가로 길이의 30% 만 스와이프 되도록
        val maxSwipe: Float = -clamp

        // RIGHT 방향으로 swipe 제한
        val right: Float = 0f

        val x = if (isClamped) {
            // View가 고정되었을 때 swipe되는 영역 제한
            if (isCurrentlyActive) dX - clamp else -clamp
        } else {
            dX
        }

        return min(max(maxSwipe, x), right) // 오른쪽 스와이프 제한(최대 0), 왼쪽 스와이프 제한(최소 maxSwipe)
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return defaultValue * 10
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        val isClamped = currentDx <= -clamp
        setIsClamped(viewHolder.adapterPosition, isClamped)
        return 2f
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as HomeNotificationViewHolder).itemView.findViewById(R.id.cl_notification_content)
    }

    // 포지션을 키로 사용하여 clamped 상태를 저장/조회하는 메서드
    private fun setIsClamped(position: Int, isClamped: Boolean) {
        clampedMap[position] = isClamped
    }

    private fun getIsClamped(position: Int): Boolean {
        return clampedMap[position] ?: false
    }

    fun setClamp(clamp: Float) {
        this.clamp = clamp
    }

    fun removePreviousClamp(recyclerView: RecyclerView) {
        if (currentPosition == previousPosition)
            return

        previousPosition?.let {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(it) ?: return
            getView(viewHolder).translationX = 0f
            setIsClamped(it, false)  // position을 사용하여 상태 변경
            previousPosition = null
        }
    }
}