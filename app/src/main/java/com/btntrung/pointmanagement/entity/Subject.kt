package com.btntrung.pointmanagement.entity

data class Subject(
    val id: Int,
    val name: String,
    val attendancePercent: Float,
    val projectPercent: Float,
    val finalPercent: Float
)