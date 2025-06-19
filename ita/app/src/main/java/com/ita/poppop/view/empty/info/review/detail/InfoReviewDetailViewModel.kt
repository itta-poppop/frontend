package com.ita.poppop.view.empty.info.review.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R
import com.ita.poppop.view.empty.info.review.InfoReviewRVItem
import com.ita.poppop.view.empty.info.review.image.InfoReviewImageRVItem

class InfoReviewDetailViewModel : ViewModel() {

    private val _inforeviewdetailList = MutableLiveData<InfoReviewRVItem>()
    val review: LiveData<InfoReviewRVItem> = _inforeviewdetailList

    fun getInfoReviewDetail(reviewId: Int) {
        val list = when (reviewId) {
            1 -> InfoReviewRVItem(
                1,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "2시간 전",
                listOf(InfoReviewImageRVItem(1, R.drawable.main_btn_favorites_icon)),
                13,
                4,
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요."
            )
            2 -> InfoReviewRVItem(
                2,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "2시간 전",
                listOf(InfoReviewImageRVItem(1, R.drawable.main_btn_favorites_icon),
                    InfoReviewImageRVItem(2, R.drawable.main_btn_home_icon),
                    InfoReviewImageRVItem(3, R.drawable.main_btn_home_icon),
                    InfoReviewImageRVItem(4, R.drawable.main_btn_home_icon)),
                12,
                4,
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요.'거짓말과 아이', '빛과 그림자' 같은 테마도 은근 생각하게 만들더라구요."
            )
            else -> InfoReviewRVItem(
                3,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "2시간 전",
                emptyList(),
                12,
                4,
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요.'거짓말과 아이', '빛과 그림자' 같은 테마도 은근 생각하게 만들더라구요."
            )
        }
        _inforeviewdetailList.value = list
    }
}