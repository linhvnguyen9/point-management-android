package com.btntrung.pointmanagement.data.remote.student

import com.btntrung.pointmanagement.entity.Classroom
import com.btntrung.pointmanagement.entity.Student
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentService {
    @GET("api/v1/students")
    suspend fun getStudentsByClass(@Query("class_id") classId: Int) : List<Student>
}