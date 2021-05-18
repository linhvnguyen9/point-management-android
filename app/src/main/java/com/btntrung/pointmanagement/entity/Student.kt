package com.btntrung.pointmanagement.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val uid: String,
    val id: Int,
    val name: String,
    val username: String,
    val password: String,
    val avatarUrl: String,
    @SerializedName("student_code") val studentCode: String,
    val role: String
) : Parcelable
