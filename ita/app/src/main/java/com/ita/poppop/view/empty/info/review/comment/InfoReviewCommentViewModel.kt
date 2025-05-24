package com.ita.poppop.view.empty.info.review.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R

class InfoReviewCommentViewModel :ViewModel() {

    private val _inforeviewcommentList = MutableLiveData<MutableList<InfoReviewCommentRVItem>>()
    val inforeviewcommentList: LiveData<MutableList<InfoReviewCommentRVItem>> = _inforeviewcommentList

    fun getInfoReviewComment(){
        val list = mutableListOf<InfoReviewCommentRVItem>()
        /*list.clear()*/
        list.add(
            InfoReviewCommentRVItem(
                1,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "6일 전",
                "헉 ㅠㅠ 너무 가고 싶어요ㅠ",
                4,
                4
            )
        )
        list.add(
            InfoReviewCommentRVItem(
                2,
                R.drawable.main_btn_favorites_icon,
                "skyline_7",
                "6일 전",
                "헉 ㅠㅠ 너무 가고 싶어요ㅠ",
                2,
                null
            )
        )
        list.add(
            InfoReviewCommentRVItem(
                3,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "6일 전",
                "헉 ㅠㅠ 너무 가고 싶어요ㅠ",
                3,
                3
            )
        )

        _inforeviewcommentList.value = list
    }
}