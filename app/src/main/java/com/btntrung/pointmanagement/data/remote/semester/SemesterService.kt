package com.btntrung.pointmanagement.data.remote.semester

import com.btntrung.pointmanagement.entity.Semester
import retrofit2.http.GET

interface SemesterService {
    @GET("api/v1/semesters")
    suspend fun getSemesters() : List<Semester>
}