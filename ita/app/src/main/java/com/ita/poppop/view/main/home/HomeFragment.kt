package com.ita.poppop.view.main.home

import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentHomeBinding
import com.ita.poppop.util.DimManager
import com.ita.poppop.view.main.MainFragmentDirections
import com.ita.poppop.view.main.home.direction.HomeDirectionAdapter
import com.ita.poppop.view.main.home.direction.HomeDirectionItemDecoration
import com.ita.poppop.view.main.home.trend.HomeTrendAdapter
import com.ita.poppop.view.main.home.trend.HomeTrendItemDecoration
import com.ita.poppop.view.main.home.upcoming.HomeUpcomingAdapter
import com.ita.poppop.view.main.home.upcoming.HomeUpcomingItemDecoration
import com.ita.poppop.view.main.home.waiting.HomeWaitingAdapter
import com.ita.poppop.view.main.home.waiting.HomeWaitingItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var dimManager: DimManager

    override fun initView() {
        dimManager = DimManager(requireActivity().window)

        setupBackPress()
        setupAnimations()
        setupDimFab()

        setupTrendRecycler()
        setupDirectionRecycler()
        setupUpcomingRecycler()
        setupWaitingRecycler()

        setupClickListeners()

    }
    private fun setupAnimations() {
        fabOpen = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close)
    }

    private fun setupDimFab() = with(binding) {
        dimManager.addFabButtons(listOf(fab1, fab2))
        dimManager.bindToggleButton(ibFab)

        ibFab.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) dimManager.showDim()
            else dimManager.hideDim()
        }
    }

    private fun setupTrendRecycler() = with(binding.rvTrend) {
        val trendList = mutableListOf(1, 2, 3, 4, 5, 6)
        adapter = HomeTrendAdapter(trendList)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(HomeTrendItemDecoration())
    }

    private fun setupDirectionRecycler() = with(binding.rvDirection) {
        adapter = HomeDirectionAdapter()
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(HomeDirectionItemDecoration())
    }

    private fun setupWaitingRecycler() = with(binding.rvWaiting) {
        val waitingList = mutableListOf(1, 2, 3, 4, 5, 6)
        adapter = HomeWaitingAdapter(waitingList)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(HomeWaitingItemDecoration())
    }

    private fun setupUpcomingRecycler() = with(binding.rvUpcoming) {
        val upcomingList = mutableListOf(0, 1, 2, 3)
        adapter = HomeUpcomingAdapter(upcomingList)
        layoutManager = GridLayoutManager(context, 2)
        addItemDecoration(HomeUpcomingItemDecoration())
    }

    private fun setupClickListeners() = with(binding) {
        tvWaitingTitle.setOnClickListener {
            navigateTo(MainFragmentDirections.actionMainFragmentToNaviHomeStory())
        }
        clSearchArea.setOnClickListener {
            navigateTo(MainFragmentDirections.actionMainFragmentToNaviHomeSearch())
        }
        ibNotification.setOnClickListener {
            navigateTo(MainFragmentDirections.actionMainFragmentToNaviHomeNotification())
        }
        ibUpcomingDetail.setOnClickListener {
            navigateTo(MainFragmentDirections.actionMainFragmentToNaviHomeUpcoming())
        }

        fab1.setOnClickListener {
            navigateTo(MainFragmentDirections.actionMainFragmentToNaviUploadWaiting())
            dimManager.hideDim()
        }
        fab2.setOnClickListener {
            navigateTo(MainFragmentDirections.actionMainFragmentToNaviUploadWaiting())
            dimManager.hideDim()
        }

    }

    private fun navigateTo(action: NavDirections) {
        val navController = requireActivity().findNavController(R.id.fcv_main_activity_container)
        navController.navigate(action)
    }

    private fun setupBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (dimManager.isDimmed()) {
                        dimManager.hideDim()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                }
            })
    }
}


