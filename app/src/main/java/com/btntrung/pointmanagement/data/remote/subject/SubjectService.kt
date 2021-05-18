package com.btntrung.pointmanagement.data.remote.subject

import com.btntrung.pointmanagement.entity.Subject
import retrofit2.http.GET

interface SubjectService {
    @GET("api/v1/subjects")
    suspend fun getSubjects(): List<Subject>
}