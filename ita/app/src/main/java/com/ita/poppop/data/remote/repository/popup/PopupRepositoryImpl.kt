package com.ita.poppop.data.remote.repository.popup

import com.ita.poppop.data.remote.api.PopupApi
import com.ita.poppop.data.remote.dto.PopupItem


//class TrendRepositoryImpl : TrendRepository {
//    private val service = RetrofitClient.getRetrofit()!!.create(TrendApi::class.java)
//
//    override suspend fun getTrendPopups(
//        token: String,
//        page: Int,
//        size: Int
//    ): Response<GetTrendResponse> {
//        val response = service.getTrends("Bearer $token", page, size)
//
//        return response
//    }
//}

class PopupRepositoryImpl(
    private val api: PopupApi
) : PopupRepository {

    override suspend fun getPopups(page: Int, size: Int): List<PopupItem> {
        val response = api.getTrends(page, size)

        if (response.code == "200") {
            return response.data
        } else {
            throw Exception("API 오류: ${response.message}")
        }
    }
}