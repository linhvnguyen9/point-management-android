package com.btntrung.pointmanagement.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Classroom(
    val id: Int,
    @SerializedName("class_name") val name: String,
    @SerializedName("semester_id") val semesterId: Int,
    @SerializedName("subject_id") val subjectId: Int
) : Parcelable