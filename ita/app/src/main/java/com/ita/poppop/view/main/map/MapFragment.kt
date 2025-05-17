package com.ita.poppop.view.main.map

import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentMapBinding

class MapFragment: BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    private lateinit var mapViewModel: MapViewModel

    private val mapRVAdapter by lazy {
        MapRVAdapter()
    }

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun initView() {
        binding.apply {

            mapViewModel = ViewModelProvider(this@MapFragment).get(MapViewModel::class.java)
            linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


            binding.rvMap.apply {
                layoutManager = linearLayoutManager
                adapter = mapRVAdapter

                val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                addItemDecoration(dividerItemDecoration)
            }

            mapViewModel.getMap()
            mapViewModel.mapList.observe(viewLifecycleOwner, Observer { response ->
                mapRVAdapter.submitList(response)
            })

            setBottomSheet()
            setupBottomSheet()

            // 지도 설정
            val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as com.naver.maps.map.MapFragment
            mapFragment.getMapAsync { naverMap ->
                val uiSettings = naverMap.uiSettings

                /*uiSettings.logoGravity = Gravity.BOTTOM or Gravity.START
                uiSettings.setLogoMargin(16, 16, 16, 16)*/

                uiSettings.isZoomControlEnabled = false  // +/- 버튼
                uiSettings.isCompassEnabled = false   // 나침반
                uiSettings.isScaleBarEnabled = false
                // uiSettings.isLocationButtonEnabled = true
                uiSettings.isLogoClickEnabled = false
            }

        }
    }
    private fun setupBottomSheet() {
        // BottomSheet 동작 설정
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.nsvBottomSheet)

        // 초기상태 접힘
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

    }

    private fun setBottomSheet() {
        // 현재 프래그먼트의 View의 크기 정보를 가져오기 위한 ViewTreeObserver 사용
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // 레이아웃이 그려진 후에 한 번만 실행되도록 리스너 제거
                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)

                // 프래그먼트 뷰의 전체 높이
                val fragmentHeight = binding.root.height

                // 바텀시트 설정
                val bottomSheetBehavior = BottomSheetBehavior.from(binding.nsvBottomSheet)

                // dp 값을 픽셀로 변환
                val topMarginPx = fragmentHeight - dpToPx(620)
                val bottomMarginPx = dpToPx(253)

                // 바텀시트 최소 높이
                val peekHeight = bottomMarginPx

                // 바텀시트 최대 높이
                val expandedOffset = topMarginPx

                // 중간 상태의 높이 (최소와 최대의 중간 값)
                val halfExpandedRatio = 0.5f

                bottomSheetBehavior.apply {
                    this.peekHeight = peekHeight
                    this.isFitToContents = false  // 중간 상태 사용
                    this.halfExpandedRatio = halfExpandedRatio
                    this.expandedOffset = expandedOffset
                    this.state = BottomSheetBehavior.STATE_COLLAPSED

                    // 중간 상태(STATE_HALF_EXPANDED) 활성화
                    this.saveFlags = BottomSheetBehavior.SAVE_ALL
                }


                // 슬라이드 중간 상태 사용
                bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        // 상태 변경 처리
                        when (newState) {
                            BottomSheetBehavior.STATE_COLLAPSED -> {
                                Log.d("BottomSheet", "상태: 접힘(COLLAPSED)")
                                // 접힌 상태에서 맨 위로 스크롤
                                binding.nsvSs.scrollTo(0, 0)
                            }
                            BottomSheetBehavior.STATE_EXPANDED -> {
                                Log.d("BottomSheet", "상태: 확장(EXPANDED)")
                            }
                            BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                                Log.d("BottomSheet", "상태: 반접힘(HALF_EXPANDED)")
                                // 반접힌 상태에서 맨 위로 스크롤
                                binding.nsvSs.scrollTo(0, 0)
                            }
                        }

                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        // 슬라이드 오프셋에 따른 상태 결정
                        when {
                            slideOffset > 0.6f && bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED -> {
                                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                            }
                            slideOffset in 0.25f..0.75f &&
                                    bottomSheetBehavior.state != BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

                            }
                            slideOffset < 0.25f && bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED -> {
                                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

                            }
                        }
                    }
                })
            }
        })
    }

    // dp -> px
    private fun dpToPx(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

}