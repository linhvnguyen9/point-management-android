package com.btntrung.pointmanagement.presentation.student;

import com.btntrung.pointmanagement.presentation.student.model.StudentPointModel;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentGetPointService {
    private String token="Bearer "+ Hawk.get("FIREBASE_TOKEN", "");

}
