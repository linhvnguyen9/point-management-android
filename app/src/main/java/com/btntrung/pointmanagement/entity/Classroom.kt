package com.btntrung.pointmanagement.entity

import com.google.gson.annotations.SerializedName

data class Classroom(val id: Int, @SerializedName("class_name") val name: String)