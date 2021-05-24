package com.btntrung.pointmanagement.presentation.student;

import com.btntrung.pointmanagement.entity.Semester;
import com.btntrung.pointmanagement.presentation.student.model.StudentPointModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    String token= Hawk.get("FIREBASE_TOKEN", "");
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MMM-dd HH:mm:ss").create();
    ApiService apiService =new Retrofit.Builder()
            .baseUrl("http://192.168.1.7:8080/")
               .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);


    @GET("api/v1/points")
    Call<List<StudentPointModel>> getAllPoint(@Header("Authorization") String token, @Query("student") String uid);

    @GET("api/v1/semesters")
    Call<List<Semester>> getAllSemester(@Header("Authorization") String token);
}
