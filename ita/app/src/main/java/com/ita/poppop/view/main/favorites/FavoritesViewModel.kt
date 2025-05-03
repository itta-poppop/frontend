package com.ita.poppop.view.main.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R

class FavoritesViewModel: ViewModel() {
    private val _favoritesList = MutableLiveData<MutableList<FavoritesRVItem>>()
    val favoritesList: LiveData<MutableList<FavoritesRVItem>> = _favoritesList

    fun getFavorites(){
        val list = mutableListOf<FavoritesRVItem>()
        /*list.clear()*/
        list.add(
            FavoritesRVItem(
                1,
                R.drawable.empty_state_logo,
                "수원시 서둔동",
                "K현대미술관 X 우주먼지...",
                "25.02.28 - 25.03.30",
                "종료 D-3"
            )
        )
        list.add(
            FavoritesRVItem(
                2,
                R.drawable.empty_state_logo,
                "경기 김포시 양촌읍",
                "곽철이 X 더 닐라이 팝업스...",
                "25.03.31 - 25.03.31",
                "종료 D-12"
            )
        )
        list.add(
            FavoritesRVItem(
                3,
                R.drawable.empty_state_logo,
                "서울 성동구",
                "르세라핌 2025 S/S 팝업...",
                "25.03.17 - 25.03.30",
                "종료 D-9"
            )
        )
        _favoritesList.value = list
    }

}