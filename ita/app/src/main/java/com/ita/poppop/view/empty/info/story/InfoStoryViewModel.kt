package com.ita.poppop.view.empty.info.story

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R

class InfoStoryViewModel: ViewModel() {

    private val _infostoryList = MutableLiveData<MutableList<InfoStoryRVItem>>()
    val infostoryList: LiveData<MutableList<InfoStoryRVItem>> = _infostoryList

    fun getInfoStory(){
        val list = mutableListOf<InfoStoryRVItem>()
        /*list.clear()*/
        list.add(
            InfoStoryRVItem(
                1,
                R.drawable.main_btn_favorites_icon,
                "jessica.mart"
            )
        )
        list.add(
            InfoStoryRVItem(
                2,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal"
            )
        )
        list.add(
            InfoStoryRVItem(
                3,
                R.drawable.main_btn_favorites_icon,
                "jessica.mart"
            )
        )
        list.add(
            InfoStoryRVItem(
                4,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal"
            )
        )
        list.add(
            InfoStoryRVItem(
                5,
                R.drawable.main_btn_favorites_icon,
                "jessica.mart"
            )
        )
        list.add(
            InfoStoryRVItem(
                6,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal"
            )
        )
        list.add(
            InfoStoryRVItem(
                7,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal"
            )
        )
        _infostoryList.value = list
        Log.d("InfoStoryViewModel", "getInfoStory called, list size: ${list.size}")
    }
}