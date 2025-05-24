package com.ita.poppop.view.empty.info.review.comment


data class InfoReviewCommentRVItem(
    var itemId: Int,
    var profileImage: Int,
    var username: String,
    var time: String,
    var content: String,
    var hearts: Int,
    var reply: Int?
)

