package com.ita.poppop.view.empty.info.review.detail.reply

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.databinding.ItemInfoReviewDetailReplyBinding
import com.ita.poppop.view.empty.info.review.InfoReviewDetailReplyRVItem

class InfoReviewDetailReplyRVAdapter: ListAdapter<InfoReviewDetailReplyRVItem, InfoReviewDetailReplyRVAdapter.InfoReviewDetailReplyViewHolder>(
    InfoReviewDetailReplyDiffutillCallback()
) {

    class InfoReviewDetailReplyViewHolder(val binding: ItemInfoReviewDetailReplyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InfoReviewDetailReplyRVItem) {
            binding.apply {
                ivReplyProfile.setImageResource(item.profileImage)
                tvReplyUsername.text = item.username
                tvReplyTime.text = item.time
                tvReplyContent.text = item.reply

            }
        }
    }

    class InfoReviewDetailReplyDiffutillCallback : DiffUtil.ItemCallback<InfoReviewDetailReplyRVItem>() {
        override fun areItemsTheSame(oldItem: InfoReviewDetailReplyRVItem, newItem: InfoReviewDetailReplyRVItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: InfoReviewDetailReplyRVItem,
            newItem: InfoReviewDetailReplyRVItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoReviewDetailReplyViewHolder {
        val binding = ItemInfoReviewDetailReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoReviewDetailReplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoReviewDetailReplyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}