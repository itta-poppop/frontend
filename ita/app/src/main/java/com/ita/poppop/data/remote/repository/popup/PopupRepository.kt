package com.ita.poppop.data.remote.repository.popup

import com.ita.poppop.data.remote.dto.PopupItem


//interface TrendRepository {
//    @GET("/api/v1/popups/trend")
//    suspend fun getTrendPopups(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): Response<GetTrendResponse>
//}
interface PopupRepository {
    suspend fun getPopups(page: Int, size: Int): List<PopupItem>
}