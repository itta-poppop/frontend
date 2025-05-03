package com.ita.poppop.view.main.home

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentFavoritesBinding
import com.ita.poppop.databinding.FragmentHomeBinding
import com.ita.poppop.view.main.home.direction.HomeDirectionAdapter
import com.ita.poppop.view.main.home.direction.HomeDirectionItemDecoration
import com.ita.poppop.view.main.home.trend.HomeTrendAdapter
import com.ita.poppop.view.main.home.trend.HomeTrendItemDecoration
import com.ita.poppop.view.main.home.upcoming.HomeUpcomingAdapter
import com.ita.poppop.view.main.home.upcoming.HomeUpcomingItemDecoration
import com.ita.poppop.view.main.home.waiting.HomeWaitingAdapter
import com.ita.poppop.view.main.home.waiting.HomeWaitingItemDecoration
import com.ita.poppop.view.main.home.waiting.HomeWaitingViewHolder


class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initView() {
        binding.apply {
            val myList = mutableListOf(0,1,2,3,4,5)

            val historyAdapter = HomeTrendAdapter(myList)
            rvTrend.addItemDecoration(HomeTrendItemDecoration())
            rvTrend.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvTrend.adapter = historyAdapter


            val directionAdapter = HomeDirectionAdapter(myList)
            rvDirection.addItemDecoration(HomeDirectionItemDecoration())
            rvDirection.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvDirection.adapter = directionAdapter


            val waitingAdapter = HomeWaitingAdapter(myList)
            rvWaiting.addItemDecoration(HomeWaitingItemDecoration())
            rvWaiting.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvWaiting.adapter = waitingAdapter
            
            val upcomingList = mutableListOf(0,1,2,3)

            val upcomingAdapter = HomeUpcomingAdapter(upcomingList)
            rvUpcoming.addItemDecoration(HomeUpcomingItemDecoration())
            rvUpcoming.layoutManager = GridLayoutManager(requireContext(), 2)
            rvUpcoming.adapter = upcomingAdapter

        }
    }
}