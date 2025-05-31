package com.ita.poppop.view.empty.info.review

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.databinding.ItemInfoReviewBinding
import com.ita.poppop.view.empty.info.review.image.InfoReviewImageRVAdapter

class InfoReviewRVAdapter: ListAdapter<InfoReviewRVItem, InfoReviewRVAdapter.InfoReviewViewHolder>(
InfoReviewDiffutillCallback()
) {

    interface InfoReviewItemClickListener{
        fun onItemClick(position: Int)
    }
    private lateinit var infoReviewItemClickListener : InfoReviewItemClickListener

    fun setInfoReviewItemClickListener(itemClickListener: InfoReviewItemClickListener){
        infoReviewItemClickListener = itemClickListener
    }

    class InfoReviewViewHolder(val binding: ItemInfoReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InfoReviewRVItem) {
            binding.apply {
                ivReviewProfile.setImageResource(item.profileImage)
                tvReviewUsername.text = item.username
                tvReviewTime.text = item.time
                tvReviewHeart.text = item.hearts.toString()
                tvReviewComment.text = item.comments.toString()
                tvReviewContent.text = item.content

                // rvadapter 연결
                val imgAdapter = InfoReviewImageRVAdapter()

                imgAdapter.submitList(item.reviewImage)

                rvReviewImage.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = imgAdapter
                }
            }
        }
    }

    class InfoReviewDiffutillCallback : DiffUtil.ItemCallback<InfoReviewRVItem>() {
        override fun areItemsTheSame(oldItem: InfoReviewRVItem, newItem: InfoReviewRVItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: InfoReviewRVItem,
            newItem: InfoReviewRVItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoReviewViewHolder {
        val binding = ItemInfoReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoReviewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            infoReviewItemClickListener.onItemClick(position)
        }
    }
}