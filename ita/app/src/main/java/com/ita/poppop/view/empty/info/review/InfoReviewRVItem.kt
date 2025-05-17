package com.ita.poppop.view.empty.info.review

import com.ita.poppop.view.empty.info.review.image.InfoReviewImageRVItem

data class InfoReviewRVItem(
    var itemId: Int,
    var profileImage: Int,
    var username: String,
    var time: String,
    var reviewImage: List<InfoReviewImageRVItem>,
    var hearts: Int,
    var comments: Int,
    var content: String
)
