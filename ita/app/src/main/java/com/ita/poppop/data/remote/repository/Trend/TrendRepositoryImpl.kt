package com.ita.poppop.data.remote.repository.Trend

import com.ita.poppop.data.remote.api.TrendApi
import com.ita.poppop.data.remote.dto.GetTrendResponse
import com.ita.poppop.data.remote.dto.TrendItem
import retrofit2.Response

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

class TrendRepositoryImpl(
    private val api: TrendApi
) : TrendRepository {

    override suspend fun getTrends(page: Int, size: Int): List<TrendItem> {
        val response = api.getTrends(page, size)

        if (response.code == "200") {
            return response.data
        } else {
            throw Exception("API 오류: ${response.message}")
        }
    }
}