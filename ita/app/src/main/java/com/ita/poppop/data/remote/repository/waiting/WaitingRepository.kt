package com.ita.poppop.data.remote.repository.waiting

import okhttp3.Response

//interface WaitingRepository {
//    @GET("/api/alarms/activity")
//    suspend fun getActivityAlarm(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int
//    ): Response<GetAlarmResponse>
//
//    @GET("/api/alarms/exhibition")
//    suspend fun getExhibitionAlarm(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int
//    ): Response<GetAlarmResponse>
//
//    @PATCH("/api/alarms/check/{alarmId}")
//    suspend fun patchAlarmChecked(
//        @Header("Authorization") token: String,
//        @Path("alarmId") alarmId: Int
//    ): Response<OnlyMsgResponse>
//}