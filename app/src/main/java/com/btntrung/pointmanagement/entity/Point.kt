package com.btntrung.pointmanagement.entity

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("attendance_point") val attendance: Float,
    @SerializedName("test_point") val test: Float,
    @SerializedName("project_point") val project: Float,
    @SerializedName("final_point") val final: Float,
    val avg: Float,
    @SerializedName("student_id") val studentId: String,
    @SerializedName("semester_id") val semesterId: Int,
    @SerializedName("manager_id") val managerId: String,
    @SerializedName("subject_id") val subjectId: Int,
    val id: Int? = null,
)
