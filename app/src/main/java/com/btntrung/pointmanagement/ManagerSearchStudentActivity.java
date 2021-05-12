package com.btntrung.pointmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.btntrung.pointmanagement.adapter.ManagerSearchStudentAdapter;
import com.btntrung.pointmanagement.entity.ClassRoom;
import com.btntrung.pointmanagement.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ManagerSearchStudentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btn_search;
    private TextView textView;
    private EditText key;
    private ManagerSearchStudentAdapter adapter;
    private List<Student> students=new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_search_student);

        recyclerView=findViewById(R.id.recycle_view);
        btn_search=findViewById(R.id.btn_search);
        key=findViewById(R.id.edit_search);
        textView=findViewById(R.id.textView);

//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Student student=new Student("1","1","Ducanh","","","","","");
        Student student1=new Student("1","1","Ducanh","","","","","");
        students.add(student);
        students.add(student1);

        intent=getIntent();
        String className=intent.getStringExtra("class").toString();
        textView.setText(className);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=key.getText().toString();

                adapter=new ManagerSearchStudentAdapter(ManagerSearchStudentActivity.this,students);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}