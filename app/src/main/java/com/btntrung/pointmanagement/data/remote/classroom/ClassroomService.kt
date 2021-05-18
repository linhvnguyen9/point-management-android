package com.btntrung.pointmanagement.data.remote.classroom

import com.btntrung.pointmanagement.entity.Classroom
import com.btntrung.pointmanagement.entity.Semester
import retrofit2.http.GET
import retrofit2.http.Query

interface ClassroomService {
    @GET("api/v1/classrooms")
    suspend fun getClassroomBySemester(@Query("semester_id") semesterId: Int) : List<Classroom>
}