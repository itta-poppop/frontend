package com.ita.poppop.view.main.info.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R
import com.ita.poppop.view.main.info.recommend.InfoRecommendRVItem
import com.ita.poppop.view.main.info.review.image.InfoReviewImageRVItem

class InfoReviewViewModel : ViewModel() {
    private val _inforeviewList = MutableLiveData<MutableList<InfoReviewRVItem>>()
    val inforeviewList: LiveData<MutableList<InfoReviewRVItem>> = _inforeviewList

    fun getInfoReview(){
        val list = mutableListOf<InfoReviewRVItem>()
        /*list.clear()*/
        list.add(
            InfoReviewRVItem(
                1,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "2시간 전",
                listOf(InfoReviewImageRVItem(1, R.drawable.main_btn_favorites_icon)),
                12,
                4,
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요.'거짓말과 아이', '빛과 그림자' 같은 테마도 은근 생각하게 만들더라구요."
            )
        )
        list.add(
            InfoReviewRVItem(
                2,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "2시간 전",
                listOf(InfoReviewImageRVItem(1, R.drawable.main_btn_favorites_icon), InfoReviewImageRVItem(2, R.drawable.main_btn_home_icon)),
                12,
                4,
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요.'거짓말과 아이', '빛과 그림자' 같은 테마도 은근 생각하게 만들더라구요."
            )
        )
        list.add(
            InfoReviewRVItem(
                3,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "2시간 전",
                listOf(InfoReviewImageRVItem(1, R.drawable.main_btn_favorites_icon)),
                12,
                4,
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요.'거짓말과 아이', '빛과 그림자' 같은 테마도 은근 생각하게 만들더라구요."
            )
        )
        list.add(
            InfoReviewRVItem(
                4,
                R.drawable.main_btn_favorites_icon,
                "wild_zeal",
                "2시간 전",
                listOf(InfoReviewImageRVItem(1, R.drawable.main_btn_favorites_icon)),
                12,
                4,
                "전시가 애니 속 장면들을 잘 살려놔서 보는 내내 몰입감 장난 아니었어요.'거짓말과 아이', '빛과 그림자' 같은 테마도 은근 생각하게 만들더라구요."
            )
        )
        _inforeviewList.value = list
    }
}