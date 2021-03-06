package com.btntrung.pointmanagement.data.remote.di

import com.btntrung.pointmanagement.data.remote.interceptor.AuthInterceptor
import com.btntrung.pointmanagement.data.remote.classroom.ClassroomService
import com.btntrung.pointmanagement.data.remote.point.PointService
import com.btntrung.pointmanagement.data.remote.semester.SemesterService
import com.btntrung.pointmanagement.data.remote.student.StudentService
import com.btntrung.pointmanagement.data.remote.subject.SubjectService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    factory {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    factory { AuthInterceptor() }

    factory {
        OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<AuthInterceptor>())
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("http://192.168.1.7:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(SemesterService::class.java) }
    factory { get<Retrofit>().create(ClassroomService::class.java) }
    factory { get<Retrofit>().create(StudentService::class.java) }
    factory { get<Retrofit>().create(PointService::class.java) }
    factory { get<Retrofit>().create(SubjectService::class.java) }
}