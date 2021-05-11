package com.btntrung.pointmanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.entity.Semester;

import java.util.ArrayList;

public class StudentPointFragment extends Fragment {
    private Spinner spinnerSemester;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_student_point, container, false);
        spinnerSemester=(Spinner) view.findViewById(R.id.semester);
        String [] semesters={"1","2","3","4"};

//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,semesters);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        this.spinnerSemester.setAdapter(adapter);

//        spinnerSemester.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), "Selected "  ,Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;

    }
}