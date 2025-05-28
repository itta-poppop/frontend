package com.ita.poppop.view.empty.notification.holder

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.databinding.ItemHomeNotificationLayoutBinding

class HomeNotificationAdapter(
    private var activity :Activity,
    private var items : MutableList<Int>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        return HomeNotificationViewHolder(ItemHomeNotificationLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeNotificationViewHolder).bind()

        // 나중 참고

        holder.itemView.setOnClickListener {
//            val parentNavController = activity.findNavController(R.id.fcv_main_activity_container)
//            val action =HomeNotificationFragmentDirections.actionHomeNotificationFragmentToEditProfileFragment2()
//            parentNavController.navigate(action)
        }
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