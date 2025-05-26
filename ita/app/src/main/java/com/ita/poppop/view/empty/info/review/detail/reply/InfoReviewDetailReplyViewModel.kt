package com.ita.poppop.view.empty.info.review.detail.reply

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R
import com.ita.poppop.view.empty.info.review.InfoReviewDetailReplyRVItem
import com.ita.poppop.view.empty.info.review.InfoReviewRVItem
import com.ita.poppop.view.empty.info.review.image.InfoReviewImageRVItem

class InfoReviewDetailReplyViewModel : ViewModel() {
    private val _inforeviewdetailreplyList =
        MutableLiveData<MutableList<InfoReviewDetailReplyRVItem>>()
    val inforeviewdetailreplyList: LiveData<MutableList<InfoReviewDetailReplyRVItem>> =
        _inforeviewdetailreplyList

    fun getInfoReviewDetailReply() {
        val list = mutableListOf<InfoReviewDetailReplyRVItem>()
        /*list.clear()*/
        list.add(
            InfoReviewDetailReplyRVItem(
                1,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "2시간 전",
                "한번 방문해보세요."
            )
        )
        list.add(
            InfoReviewDetailReplyRVItem(
                2,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "6일전",
                "몇시쯤에 가셨어요?"
            )
        )

        _inforeviewdetailreplyList.value = list
    }
}