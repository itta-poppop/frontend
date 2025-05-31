package com.ita.poppop.view.empty.info.recommend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R

class InfoRecommendViewModel: ViewModel() {
    private val _inforecommendList = MutableLiveData<MutableList<InfoRecommendRVItem>>()
    val inforecommendList: LiveData<MutableList<InfoRecommendRVItem>> = _inforecommendList

    fun getInfoRecommend(){
        val list = mutableListOf<InfoRecommendRVItem>()
        /*list.clear()*/
        list.add(
            InfoRecommendRVItem(
                1,
                R.drawable.main_btn_favorites_icon,
                "KT ROLSTER 팝업스토어",
                "수원시 서둔동"
            )
        )
        list.add(
            InfoRecommendRVItem(
                2,
                R.drawable.main_btn_favorites_icon,
                "KT ROLSTER 팝업스토어",
                "수원시 서둔동"
            )
        )
        list.add(
                InfoRecommendRVItem(
                    3,
                    R.drawable.main_btn_favorites_icon,
                    "KT ROLSTER 팝업스토어",
                    "수원시 서둔동"
                )
        )
        list.add(
            InfoRecommendRVItem(
                4,
                R.drawable.main_btn_favorites_icon,
                "KT ROLSTER 팝업스토어",
                "수원시 서둔동"
            )
        )
        _inforecommendList.value = list
    }

}