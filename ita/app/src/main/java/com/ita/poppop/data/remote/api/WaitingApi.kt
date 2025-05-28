package com.ita.poppop.data.remote.api

import com.ita.poppop.data.remote.dto.GetWaitingResponse
import okhttp3.Response


//interface WaitingApi {
//    @GET("/api/alarms/activity")
//    suspend fun getActivityAlarm(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int
//    ): Response<GetWaitingResponse>
//
//    @GET("/api/alarms/exhibition")
//    suspend fun getExhibitionAlarm(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int
//    ): Response<GetWaitingResponse>
//
//    @PATCH("/api/alarms/check/{alarmId}")
//    suspend fun patchAlarmChecked(
//        @Header("Authorization") token: String,
//        @Path("alarmId") alarmId: Int
//    ): Response<OnlyMsgResponse>
//}