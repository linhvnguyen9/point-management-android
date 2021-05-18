package com.btntrung.pointmanagement.presentation.student;

import android.provider.ContactsContract;
import android.view.View;

import com.btntrung.pointmanagement.entity.Semester;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentGetPointService{
    private String token="Bearer "+ Hawk.get("FIREBASE_TOKEN", "");
    private List<Semester> data;

    public void setData(List<Semester> data) {
        this.data = data;
    }

    public List<Semester> getData() {
        ApiService.apiService.getAllSemester(token).enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                System.out.println("da call thanh cong");
                setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {

            }
        });
        return data;
    }

    private void callSemester() {
        System.out.println(token);

    }

}
