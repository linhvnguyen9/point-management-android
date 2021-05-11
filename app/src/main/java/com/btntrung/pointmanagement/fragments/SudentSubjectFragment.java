package com.btntrung.pointmanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.adapter.StudentSubjectAdapter;
import com.btntrung.pointmanagement.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class SudentSubjectFragment extends Fragment {
    private RecyclerView recyclerView;
    private StudentSubjectAdapter subjectAdapter;
    private List<Subject> subjects=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_sudent_subject,container,false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Subject subject=new Subject(1,"Lap trinh",0,0,0);
        Subject subject2=new Subject(2,"Lap trinh nhung",0,0,0);
        Subject subject3=new Subject(3,"Kien truc thiet ke phan mem",0,0,0);
        subjects.add(subject);
        subjects.add(subject2);
        subjects.add(subject3);
        subjectAdapter=new StudentSubjectAdapter(getContext(),subjects);
        recyclerView.setAdapter(subjectAdapter);
        return view;
    }
}