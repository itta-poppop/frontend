package com.ita.poppop.view.main.home.direction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.databinding.ItemHomeDirectionLayoutBinding




class HomeDirectionAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val directionList = mutableListOf(
        Pair("성수", "성수역"),
        Pair("여의도", "여의도역"),
        Pair("홍대", "홍대역"),
        Pair("강남", "강남역"),
        Pair("잠실", "잠실역"),
        Pair("용산", "용산역")
    )

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        return HomeDirectionViewHolder(ItemHomeDirectionLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeDirectionViewHolder).bind(getItem(position))
    }


    // 아이템 반환 메서드
    private fun getItem(position: Int): Pair<String, String> {
        return directionList[position]
    }

    // 아이템 개수 반환 메서
    override fun getItemCount(): Int = directionList.size

    // 아이템 고유 ID 반환 메서드
    override fun getItemId(position: Int): Long {
        return if (position in directionList.indices) {
            directionList[position].hashCode().toLong()
        } else {
            -1L // 아이디를 찾지 못했을 때 반환되는 기본값
        }

    }


}