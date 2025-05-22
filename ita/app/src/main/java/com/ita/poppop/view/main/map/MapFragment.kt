package com.ita.poppop.view.main.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons

class MapFragment: BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback {

    private lateinit var mapViewModel: MapViewModel

    private val mapRVAdapter by lazy {
        MapRVAdapter()
    }

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun initView() {
        binding.apply {

            mapViewModel = ViewModelProvider(this@MapFragment).get(MapViewModel::class.java)
            linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            val categoryBtn = listOf(btnMapWhole, btnMapPopup, btnMapExhibition, btnMapFestival)

            categoryBtn.forEach { button ->
                button.setOnClickListener {
                    categoryBtn.forEach { it.isSelected = false }
                    button.isSelected = true

                    when (button.id) {
                        R.id.btn_map_whole -> mapViewModel.selectCategoryBtn("전체")
                        R.id.btn_map_popup -> mapViewModel.selectCategoryBtn("팝업스토어")
                        R.id.btn_map_exhibition -> mapViewModel.selectCategoryBtn("전시")
                        R.id.btn_map_festival -> mapViewModel.selectCategoryBtn("페스티벌")
                    }
                }
            }
            mapViewModel.selectCategoryBtn.observe(viewLifecycleOwner) { category ->
                Toast.makeText(context, category, Toast.LENGTH_SHORT).show()
            }

            // 지도 설정
            rvMap.apply {
                layoutManager = linearLayoutManager
                adapter = mapRVAdapter

                val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                addItemDecoration(dividerItemDecoration)
            }
            
            mapViewModel.getMap()
            mapViewModel.mapList.observe(viewLifecycleOwner, Observer { response ->
                mapRVAdapter.submitList(response)
            })
            
            locationSource = FusedLocationSource(
                requireActivity(),
                LOCATION_PERMISSION_REQUEST_CODE
            )

            val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as com.naver.maps.map.MapFragment
            mapFragment.getMapAsync(this@MapFragment)
            
            // 바텀 시트
            setBottomSheet()
            setupBottomSheet()

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
                val topMarginPx = dpToPx(55)
                val bottomMarginPx = dpToPx(48)

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

    // 위치 권한 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (
            locationSource.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
        ) {
            if (!locationSource.isActivated) { // 권한 거부시 위치 안 보이도록
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // 지도 로딩 후 자동 호출
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource

        // 위치 권한 확인
        checkLocationPermission()

        // 실내지도 활성화
        naverMap.isIndoorEnabled = true

        // 마커 저장 리스트
        val markers = mutableListOf<Marker>()
        // 마커 위경도
        val markerPositions = listOf(
            LatLng(37.5670, 126.9780),
            LatLng(37.5680, 126.9790),
            LatLng(37.5660, 126.9770)
        )
        
        // 마커 커스텀
        markerPositions.forEach { position ->
            val marker = Marker().apply {
                this.position = position
                this.map = naverMap
                //this.icon = OverlayImage.fromResource(R.drawable.app_logo)
                this.icon = MarkerIcons.BLACK
                this.width = dpToPx(50)
                this.height = dpToPx(63)
            }
            markers.add(marker)
        }
        
        // 줌 일정 레벨 이하일 경우 마커 숨기기
        naverMap.addOnCameraChangeListener { reason, animated ->
            val zoom = naverMap.cameraPosition.zoom
            val shouldShow = zoom >= 14.5
            markers.forEach { it.isVisible = shouldShow }
        }


        val uiSettings = naverMap.uiSettings
        uiSettings.isZoomControlEnabled = false // +/- 버튼
        uiSettings.isCompassEnabled = false  // 나침반
        uiSettings.isScaleBarEnabled = false
        uiSettings.isLocationButtonEnabled = false // 현위치 버튼
        uiSettings.isLogoClickEnabled = false

        // 위치 권한 확인 후 위치 추적 모드 설정
        binding.fabSearchTracking.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한 없으면 요청
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // 권한 있으면 위치 추적 모드 켜기
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}
