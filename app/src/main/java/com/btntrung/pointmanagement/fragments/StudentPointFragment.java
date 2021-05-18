package com.btntrung.pointmanagement.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.adapter.StudentPointAdapter;
import com.btntrung.pointmanagement.entity.Semester;
import com.btntrung.pointmanagement.entity.Subject;
import com.btntrung.pointmanagement.presentation.student.ApiService;
import com.btntrung.pointmanagement.presentation.student.StudentGetPointService;
import com.btntrung.pointmanagement.presentation.student.model.StudentPointModel;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentPointFragment extends Fragment {
    private Spinner spinnerSemester;
    private String token="Bearer "+ Hawk.get("FIREBASE_TOKEN", "");

    private RecyclerView recyclerView;
    private StudentPointAdapter adapterPoint;
    private List<StudentPointModel> listSubjects=new ArrayList<>();
    private String keySemester;
    private int studentID=4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_student_point, container, false);
        spinnerSemester=(Spinner) view.findViewById(R.id.semester);
        recyclerView=view.findViewById(R.id.recycle_view);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        callSemester(view);



        return view;

    }
    private void callSemester(View view){
        System.out.println(token);
        ApiService.apiService.getAllSemester(token).enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                List<Semester> listSemester=response.body();
                List<String> semesterName=new ArrayList<>();

                for (Semester semesters:listSemester)
                    semesterName.add(semesters.getName());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (view.getContext(),R.layout.support_simple_spinner_dropdown_item,semesterName);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSemester.setAdapter(adapter);

                spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        callSubject(view,listSemester.get(position).getName(),studentID);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {

            }
        });

    }

    private void callSubject(View view,String semesterNameKey,int studentID) {
        ApiService.apiService.getAllPoint(token, studentID).enqueue(new Callback<List<StudentPointModel>>() {
            @Override
            public void onResponse(Call<List<StudentPointModel>> call, Response<List<StudentPointModel>> response) {
                List<StudentPointModel> listSubject=response.body();
                List<StudentPointModel> listFilter=new ArrayList<>();
                for (StudentPointModel point:listSubject)
                    if (point.getSemester().getName().equals(semesterNameKey))
                        listFilter.add(point);

                adapterPoint=new StudentPointAdapter(getContext(),listFilter);
                recyclerView.setAdapter(adapterPoint);
            }

            @Override
            public void onFailure(Call<List<StudentPointModel>> call, Throwable t) {

            }
        });
    }
}