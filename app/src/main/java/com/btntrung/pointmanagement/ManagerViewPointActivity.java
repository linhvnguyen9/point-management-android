package com.btntrung.pointmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.btntrung.pointmanagement.adapter.ManagerViewPointAdapter;
import com.btntrung.pointmanagement.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class ManagerViewPointActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ManagerViewPointAdapter adapter;
    List<Subject> subjects=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_point);

        recyclerView=findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Subject subject=new Subject(1,"toansfv",20,5,6);
        subjects.add(subject);
        subjects.add(subject);

        adapter=new ManagerViewPointAdapter(ManagerViewPointActivity.this,subjects);
        recyclerView.setAdapter(adapter);
    }
}