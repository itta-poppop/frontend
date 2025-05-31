package com.ita.poppop.data.remote.repository.waiting

import okhttp3.Response

//class WaitingRepositoryImpl: AlarmRepository {
//    private val service = RetrofitClient.getRetrofit()!!.create(AlarmApi::class.java)
//
//    override suspend fun getActivityAlarm(token: String, page: Int): Response<GetAlarmResponse> {
//        val response = service.getActivityAlarm("Bearer $token", page)
//
//        return if(response.isSuccessful && response.body()!!.check){
//            response
//        } else {
//            response
//        }
//    }
//
//    override suspend fun getExhibitionAlarm(token: String, page: Int): Response<GetAlarmResponse> {
//        val response = service.getExhibitionAlarm("Bearer $token", page)
//
//        return if(response.isSuccessful && response.body()!!.check){
//            response
//        } else {
//            response
//        }
//    }
//
//    override suspend fun patchAlarmChecked(token: String, alarmId: Int): Response<OnlyMsgResponse> {
//        val response = service.patchAlarmChecked("Bearer $token", alarmId)
//
//        return if(response.isSuccessful && response.body()!!.check){
//            response
//        } else {
//            response
//        }
//    }
//}