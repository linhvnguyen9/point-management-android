package com.btntrung.pointmanagement.entity

import com.google.gson.annotations.SerializedName

data class Semester(val id: Int, @SerializedName("semester_name") val name: String) {
    override fun toString(): String = name
}
