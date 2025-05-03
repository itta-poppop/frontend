package com.ita.poppop.view.main.map

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.databinding.ItemMapBinding

class MapRVAdapter: ListAdapter<MapRVItem, MapRVAdapter.MapViewHolder>(MapDiffutillCallback()) {

    class MapViewHolder(val binding: ItemMapBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MapRVItem) {
            binding.apply {
                ivMap.setImageResource(item.imageUrl)
                tvMapTitle.text = item.title
                tvMapPeriod.text = item.period
            }
        }
    }

    class MapDiffutillCallback: DiffUtil.ItemCallback<MapRVItem>() {
        override fun areItemsTheSame(oldItem: MapRVItem, newItem: MapRVItem): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MapRVItem,
            newItem: MapRVItem
        ): Boolean {
            return oldItem === newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        val binding = ItemMapBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}