package com.ita.poppop.view.main.home

import android.graphics.RectF
import android.util.Log
import android.util.TypedValue
import android.view.WindowInsetsController
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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



class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var dimManager: DimManager

    private var isDimmed = false

    override fun initView() {
        dimManager = DimManager(requireActivity())
        binding.apply {
            // 애니메이션 초기화
            fabOpen = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open)
            fabClose = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close)

            val myList = mutableListOf(1,2,3,4,5,6)

            val historyAdapter = HomeTrendAdapter(myList)
            rvTrend.addItemDecoration(HomeTrendItemDecoration())
            rvTrend.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvTrend.adapter = historyAdapter


            val directionAdapter = HomeDirectionAdapter()
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

            tvWaitingTitle.setOnClickListener {
                val parentNavController = requireActivity().findNavController(R.id.fcv_main_activity_container)
                val action = MainFragmentDirections.actionMainFragmentToNaviHomeStory()
                parentNavController.navigate(action)
            }

            clSearchArea.setOnClickListener {
                val parentNavController = requireActivity().findNavController(R.id.fcv_main_activity_container)
                val action = MainFragmentDirections.actionMainFragmentToNaviHomeSearch()
                parentNavController.navigate(action)
            }
            ibNotification.setOnClickListener{
                val parentNavController = requireActivity().findNavController(R.id.fcv_main_activity_container)
                val action = MainFragmentDirections.actionMainFragmentToNaviHomeNotification()
                parentNavController.navigate(action)
            }


            ibFab.setOnClickListener{

                // FAB 버튼 위치 및 크기 구하기
                toggleDim()

                dimManager.clearTouchableAreas()
                listOf(fab1, fab2, ibFab).forEach { fab ->
                    val (x, y, radius) = getFabPosition(fab)
                    dimManager.addCircleTouchableArea(x, y, radius)
                }
                listOf(txFab1, txFab2).forEach { tx ->
                    val (rect,radius) = getTextViewPosition(tx)
                    dimManager.addRoundRectTouchableArea(rect, radius)
                }

//                dimManager.addTouchableView(it)
                dimManager.toggleDim()
            }


        }
    }

    private fun setupTouchableAreas() {
        // FAB 버튼 원형 터치 영역 설정
        val fabLocation = IntArray(2)
        binding.ibFab.getLocationInWindow(fabLocation)
        val fabCenterX = fabLocation[0] + binding.ibFab.width / 2f
        val fabCenterY = fabLocation[1] + binding.ibFab.height / 2f
        val fabRadius = Math.min(binding.ibFab.width, binding.ibFab.height) / 2f


        // 모든 터치 영역 초기화 후 새로 설정
        dimManager.clearTouchableAreas()
        dimManager.addCircleTouchableArea(fabCenterX, fabCenterY, fabRadius)
//        dimManager.addTouchableArea(menuRect)
    }

    private fun getFabPosition(fab : ImageButton) : Triple<Float, Float, Float>{

        val fabLocation = IntArray(2)
        fab.getLocationInWindow(fabLocation)
        val fabCenterX = fabLocation[0] + fab.width / 2f
        val fabCenterY = fabLocation[1] + fab.height / 2f
        val fabRadius = Math.min(fab.width, fab.height) / 2f
        Log.d("checkDimmer","fabCenterX : ${fabCenterX},fabCenterY : ${fabCenterY},fabRadius : ${fabRadius}")
        return Triple(fabCenterX,fabCenterY,fabRadius)
    }

    private fun getTextViewPosition(tv : TextView) : Pair<RectF, Float>{
        val tvLocation = IntArray(2)
        tv.getLocationInWindow(tvLocation)
        val tvRect = RectF(
            tvLocation[0].toFloat(),
            tvLocation[1].toFloat(),
            (tvLocation[0] + tv.width).toFloat(),
            (tvLocation[1] + tv.height).toFloat()
        )
        val tvRadius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            24f,
            requireContext().resources.displayMetrics
        )
        Log.d("checkDimmer","tvRect : ${tvRect},tvRadius : ${tvRadius}")
        return Pair(tvRect,tvRadius)
    }
    private fun toggleDim() {
        if (isDimmed) {
            hideDim()
        } else {
            showDim()
        }
    }

    private fun hideDim() {
        if (!isDimmed) return

        binding.fab1.isVisible = false
        binding.txFab1.isVisible = false
        binding.fab2.isVisible = false
        binding.txFab2.isVisible = false
        // 밝은 배경에 어두운 아이콘
        requireActivity().window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
        )
        isDimmed = false
    }

    private fun showDim() {
        if (isDimmed) return

        binding.fab1.isVisible = true
        binding.txFab1.isVisible = true
        binding.fab2.isVisible = true
        binding.txFab2.isVisible = true

        isDimmed = true
    }

    private fun showCustomDialog() {


//        val customDialogFragment = FloatingActionButtonDialog()
//        customDialogFragment.show(parentFragmentManager, "MyPageUnLinkDialog")
    }

}