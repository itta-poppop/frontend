package com.ita.poppop.view.empty.upload

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ita.poppop.R
import com.ita.poppop.databinding.ItemUploadImgBinding

class UploadRVAdapter: ListAdapter<UploadRVItem, UploadRVAdapter.UploadViewHolder>(
    UploadDiffutillCallback()
) {

    /*interface InfoReviewItemClickListener{
        fun onItemClick(position: Int)
    }
    private lateinit var infoReviewItemClickListener : InfoReviewItemClickListener

    fun setInfoReviewItemClickListener(itemClickListener: InfoReviewItemClickListener){
        infoReviewItemClickListener = itemClickListener
    }*/

    class UploadViewHolder(val binding: ItemUploadImgBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UploadRVItem) {
            Glide.with(binding.root.context)
                .load(item.uploadImage)
                .into(binding.ivImgArea)
            /*binding.apply {
                ivImgArea.setImageResource(item.uploadImage)
                Glide.with(context).load(item).into(R.id.ivImgArea)
            }*/
        }
    }

    class UploadDiffutillCallback : DiffUtil.ItemCallback<UploadRVItem>() {
        override fun areItemsTheSame(oldItem: UploadRVItem, newItem: UploadRVItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: UploadRVItem,
            newItem: UploadRVItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadViewHolder {
        val binding = ItemUploadImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UploadViewHolder(binding)
    }

    //override fun getItemCount(): Int {
        //return item.count()
    //}

    override fun onBindViewHolder(holder: UploadViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        /*holder.itemView.setOnClickListener {
            infoReviewItemClickListener.onItemClick(position)
        }*/
    }
}