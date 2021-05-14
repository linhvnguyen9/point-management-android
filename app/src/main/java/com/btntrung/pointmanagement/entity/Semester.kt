package com.btntrung.pointmanagement.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Semester(val id: Int, @SerializedName("semester_name") val name: String) : Serializable {
    override fun toString(): String = name
}
