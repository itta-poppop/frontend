package com.ita.poppop.view.empty.upcoming.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.databinding.ItemHomeSearchLayoutBinding
import com.ita.poppop.databinding.ItemUpcomingLayoutBinding
import com.ita.poppop.view.empty.search.holder.HomeSearchViewHolder


class UpcomingAdapter(
    private var items : MutableList<Int>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        return UpcomingViewHolder(ItemUpcomingLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UpcomingViewHolder).bind()
    }


    // 아이템 반환 메서드
    private fun getItem(position: Int): Int {
        return items[position]
    }

    // 아이템 개수 반환 메서
    override fun getItemCount(): Int = items.size

    // 아이템 고유 ID 반환 메서드
    override fun getItemId(position: Int): Long {
        return if (position in items.indices) {
            items[position].hashCode().toLong()
        } else {
            -1L // 아이디를 찾지 못했을 때 반환되는 기본값
        }

    }
}