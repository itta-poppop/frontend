package com.ita.poppop.data.remote.api

import com.ita.poppop.data.remote.dto.GetTrendResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//interface TrendApi {
//    @GET("/api/v1/popups/trend")
//    suspend fun getTrends(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): Response<GetTrendResponse>
//}

interface TrendApi {
    @GET("/api/v1/popups/trend")
    suspend fun getTrends(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): GetTrendResponse
}
