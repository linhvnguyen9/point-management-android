package com.btntrung.pointmanagement.entity

data class Point(
    val id: Int,
    val attendance: Float,
    val test: Float,
    val project: Float,
    val final: Float,
    val avg: Float
)
