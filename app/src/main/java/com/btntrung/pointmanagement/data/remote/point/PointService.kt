package com.btntrung.pointmanagement.data.remote.point

import com.btntrung.pointmanagement.entity.Point
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PointService {
    @POST("api/v1/points")
    suspend fun savePoint(@Body point: Point) : Response<Void>
}