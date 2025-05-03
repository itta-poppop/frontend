package com.ita.poppop.view.main.favorites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ita.poppop.databinding.ItemFavoritesBinding

class FavoritesRVAdapter: ListAdapter<FavoritesRVItem, FavoritesRVAdapter.FavoritesViewHolder>(FavoritesDiffutillCallback()) {

    class FavoritesViewHolder(val binding: ItemFavoritesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoritesRVItem) {
            binding.apply {
                ivFavorites.setImageResource(item.imageUrl)
                tvFavoritesLocation.text = item.location
                tvFavoritesTitle.text = item.title
                tvFavoritesPeriod.text = item.period
                tvFavoritesDDay.text = item.dday
            }
        }
    }

    class FavoritesDiffutillCallback: DiffUtil.ItemCallback<FavoritesRVItem>() {
        override fun areItemsTheSame(oldItem: FavoritesRVItem, newItem: FavoritesRVItem): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: FavoritesRVItem,
            newItem: FavoritesRVItem
        ): Boolean {
            return oldItem === newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}