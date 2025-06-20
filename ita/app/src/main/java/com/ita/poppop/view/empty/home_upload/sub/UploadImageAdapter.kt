package com.ita.poppop.view.empty.home_upload.sub

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ita.poppop.databinding.ItemUploadAddImageLayoutBinding
import com.ita.poppop.databinding.ItemUploadImageLayoutBinding

class UploadImageAdapter(
    private val onAddClick: () -> Unit,
    private val onDeleteClick: (Long) -> Unit
) : ListAdapter<UploadItem, RecyclerView.ViewHolder>(UploadItemDiffCallback()) {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    inner class UploadImageViewHolder(private val binding: ItemUploadImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageItem) {
            Glide.with(binding.root).load(item.uri).into(binding.ivImage)
            binding.ibDeleteImage.setOnClickListener {
                Log.d("checkTag", "id : ${item.id}")
                onDeleteClick(item.id)
            }
        }
    }

    inner class UploadAddImageViewHolder(private val binding: ItemUploadAddImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageCount: Int) {
            Log.d("checkCount","imageCount :${imageCount}")

            // 텍스트뷰에 이미지 개수 표시 (예: "3/10" 형태로 표시)
            binding.tvItemCount.text = "$imageCount/5" // 최대 개수는 필요에 따라 조정

            // 또는 단순히 개수만 표시
            // binding.tvImageCount.text = imageCount.toString()

            binding.root.setOnClickListener {
                onAddClick()
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is UploadItem.Header -> VIEW_TYPE_HEADER
            is UploadItem.Image -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> UploadAddImageViewHolder(
                ItemUploadAddImageLayoutBinding.inflate(inflater, parent, false)
            )
            else -> UploadImageViewHolder(
                ItemUploadImageLayoutBinding.inflate(inflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = currentList[position]) {
            is UploadItem.Header -> {
                // Header를 제외한 실제 이미지 개수 계산
                val imageCount = currentList.count { it is UploadItem.Image }
                (holder as UploadAddImageViewHolder).bind(imageCount)
            }
            is UploadItem.Image -> {
                (holder as UploadImageViewHolder).bind(item.imageItem)
            }
        }
    }
}

// DiffUtil 콜백 - UploadItem 기준으로 수정
class UploadItemDiffCallback : DiffUtil.ItemCallback<UploadItem>() {
    override fun areItemsTheSame(oldItem: UploadItem, newItem: UploadItem): Boolean {
        return when {
            oldItem is UploadItem.Header && newItem is UploadItem.Header -> true
            oldItem is UploadItem.Image && newItem is UploadItem.Image ->
                oldItem.imageItem.id == newItem.imageItem.id
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: UploadItem, newItem: UploadItem): Boolean {
        return when {
            // Header는 리스트 변경시마다 업데이트되도록 false 반환
            oldItem is UploadItem.Header && newItem is UploadItem.Header -> false
            oldItem is UploadItem.Image && newItem is UploadItem.Image ->
                oldItem.imageItem == newItem.imageItem
            else -> false
        }
    }
}