package com.btntrung.pointmanagement.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Subject(
    val id: Int,
    @SerializedName("subject_name") val name: String,
    @SerializedName("attendance_percent") val attendancePercent: Float,
    @SerializedName("project_percent") val projectPercent: Float,
    @SerializedName("test_percent") val testPercent: Float,
    @SerializedName("final_percent") val finalPercent: Float
):Serializable