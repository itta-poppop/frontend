package com.ita.poppop.view.empty.info.review.image

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.databinding.ItemInfoReviewImageBinding

class InfoReviewImageRVAdapter: ListAdapter<InfoReviewImageRVItem, InfoReviewImageRVAdapter.InfoReviewImageViewHolder>(
    InfoReviewImageDiffutillCallback()
)  {
    class InfoReviewImageViewHolder(val binding: ItemInfoReviewImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InfoReviewImageRVItem) {
            binding.apply {
                ivReviewImage.setImageResource(item.imageUrl)
            }
        }
    }

    class InfoReviewImageDiffutillCallback : DiffUtil.ItemCallback<InfoReviewImageRVItem>() {
        override fun areItemsTheSame(oldItem: InfoReviewImageRVItem, newItem: InfoReviewImageRVItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: InfoReviewImageRVItem,
            newItem: InfoReviewImageRVItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoReviewImageViewHolder {
        val binding = ItemInfoReviewImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoReviewImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoReviewImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}