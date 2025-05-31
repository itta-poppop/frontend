package com.ita.poppop.view.main.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R

class MapViewModel: ViewModel() {

    private val _mapList = MutableLiveData<MutableList<MapRVItem>>()
    val mapList: LiveData<MutableList<MapRVItem>> = _mapList

    private val _selectCategoryBtn = MutableLiveData<String>()
    val selectCategoryBtn: LiveData<String> = _selectCategoryBtn

    fun selectCategoryBtn(category: String) {
        _selectCategoryBtn.value = category
    }
    fun getMap(){
        val list = mutableListOf<MapRVItem>()
        list.add(
            MapRVItem(
                1,
                R.drawable.main_btn_favorites_icon,
                "TV 애니메이션 [최애의 아이]",
                "25.03.22 - 25.06.08"
            )
        )
        list.add(
            MapRVItem(
                2,
                R.drawable.map_dummy_img,
                "곽철이 X 더 닐라이 팝업스토어",
                "25.03.31 - 25.03.31"
            )
        )
        list.add(
            MapRVItem(
                3,
                R.drawable.map_dummy_img,
                "블리치전 애니메이션 20주년 기념 서울 전시",
                "25.01.25 - 25.04.03"
            )
        )
        list.add(
            MapRVItem(
                4,
                R.drawable.map_dummy_img,
                "블리치전 애니메이션 20주년 기념 서울 전시",
                "25.01.25 - 25.04.03"
            )
        )
        list.add(
            MapRVItem(
                5,
                R.drawable.map_dummy_img,
                "블리치전 애니메이션 20주년 기념 서울 전시",
                "25.01.25 - 25.04.03"
            )
        )
        _mapList.value = list
    }
}