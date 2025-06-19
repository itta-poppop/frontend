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
                4
            )
        )
        list.add(
            InfoReviewCommentRVItem(
                2,
                R.drawable.main_btn_favorites_icon,
                "skyline_7",
                "6일 전",
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요.'거짓말과 아이', '빛과 그림자' 같은 테마도 은근 생각하게 만들더라구요.",
                null
            )
        )
        list.add(
            InfoReviewCommentRVItem(
                3,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "6일 전",
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요.'거짓말과 아이', '빛과 그림자' 같은 테마도 은근 생각하게 만들더라구요.",
                3
            )
        )

        _inforeviewcommentList.value = list
    }
}