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

    // 댓글 화면에서 대댓글 삭제
    fun deleteReply(replyItemId: Int) {
        val currentList = _inforeviewdetailreplyList.value?.toMutableList() ?: return
        val index = currentList.indexOfFirst { it.itemId == replyItemId }
        if (index != -1) {
            currentList.removeAt(index)
            _inforeviewdetailreplyList.value = currentList
        }
    }

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