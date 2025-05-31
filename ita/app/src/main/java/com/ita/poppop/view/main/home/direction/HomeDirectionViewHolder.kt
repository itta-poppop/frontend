package com.ita.poppop.view.main.home.direction

import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.R
import com.ita.poppop.databinding.ItemHomeDirectionLayoutBinding

class HomeDirectionViewHolder(
    private val binding: ItemHomeDirectionLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        private val locationImageMap = mapOf(
            "성수" to R.drawable.img_seongsu,
            "여의도" to R.drawable.img_yeouido,
            "홍대" to R.drawable.img_hongdae,
            "강남" to R.drawable.img_gangnam,
            "잠실" to R.drawable.img_jamsil,
            "용산" to R.drawable.img_yongsan
        )

        private val DEFAULT_IMAGE_RES = R.drawable._notification_item_icon_background
    }


    fun bind(item: Pair<String, String>) = with(binding) {
        tvDirectionTitle.text = item.first
        tvDirectionThumbnail.setImageResource(locationImageMap[item.first] ?: DEFAULT_IMAGE_RES)
    }
}