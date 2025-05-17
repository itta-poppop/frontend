package com.ita.poppop.view.main.home.direction

import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.R
import com.ita.poppop.databinding.ItemHomeDirectionLayoutBinding

class HomeDirectionViewHolder(
    private val binding: ItemHomeDirectionLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    // 지역별 이미지 리소스 맵핑
    private val locationImageMap = mapOf(
        "성수" to R.drawable.img_seongsu,
        "여의도" to R.drawable.img_yeouido,
        "홍대" to R.drawable.img_hongdae,
        "강남" to R.drawable.img_gangnam,
        "잠실" to R.drawable.img_jamsil,
        "용산" to R.drawable.img_yongsan
    )

    fun bind(item: Pair<String, String>) {
        // 텍스트 설정
        binding.tvDirectionTitle.text = item.first

        // 이미지 설정 - 맵에서 리소스 ID 조회
        val imageResourceId = locationImageMap[item.first] ?: getDefaultImageResource()
        binding.tvDirectionThumbnail.setImageResource(imageResourceId)
    }

    // 기본 이미지 리소스 반환 (맵에서 찾지 못한 경우)
    private fun getDefaultImageResource(): Int {
        return R.drawable._notification_item_icon_background // 기본 이미지 리소스
    }
}