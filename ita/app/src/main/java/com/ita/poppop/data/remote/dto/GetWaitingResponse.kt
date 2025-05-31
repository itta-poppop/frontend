package com.ita.poppop.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GetWaitingResponse(
    @SerializedName("check")
    val check: Boolean,
    @SerializedName("information")
    val information: AlarmInformation
)

data class AlarmInformation(
    @SerializedName("hasNextPage")
    val hasNextPage: Boolean,
    @SerializedName("data")
    val data: List<AlarmEntity>
)

data class AlarmEntity(
    @SerializedName("alarmId")
    var alarmId: Int,
    @SerializedName("alarmType")
    val alarmType: String,
    @SerializedName("targetId")
    var targetId: Int,
    @SerializedName("clickId")
    var clickId: Int,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("contents")
    val contents: String,
    @SerializedName("dateTime")
    val dateTime: String,
    @SerializedName("isChecked")
    val isChecked: Boolean
)
