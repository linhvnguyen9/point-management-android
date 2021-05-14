package com.btntrung.pointmanagement.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Subject(
    val id: Int,
    @SerializedName("subject_name") val name: String,
    val attendancePercent: Float,
    val projectPercent: Float,
    val finalPercent: Float
):Serializable