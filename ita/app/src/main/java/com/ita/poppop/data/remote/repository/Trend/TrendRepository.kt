package com.ita.poppop.data.remote.repository.Trend

import com.ita.poppop.data.remote.dto.GetTrendResponse
import com.ita.poppop.data.remote.dto.TrendItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


//interface TrendRepository {
//    @GET("/api/v1/popups/trend")
//    suspend fun getTrendPopups(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): Response<GetTrendResponse>
//}
interface TrendRepository {
    suspend fun getTrends(page: Int, size: Int): List<TrendItem>
}