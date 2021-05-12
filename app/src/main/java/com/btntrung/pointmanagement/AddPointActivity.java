package com.btntrung.pointmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.btntrung.pointmanagement.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class AddPointActivity extends AppCompatActivity {
    private Button btnBack,btnAdd;
    private EditText attendance,test, project,finalPoint;
    private TextView txtStudentName;
    private Spinner subjectSpinner;
    private List<Subject> subjects=new ArrayList<>();

    private Intent intent;
    private int subjectPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point);

        btnAdd=findViewById(R.id.btn_add);
        btnBack=findViewById(R.id.btn_back);
        attendance=findViewById(R.id.attendance);
        test=findViewById(R.id.test);
        project=findViewById(R.id.profile);
        finalPoint=findViewById(R.id.finalPoint);
        subjectSpinner=findViewById(R.id.spinner_subject);
        txtStudentName=findViewById(R.id.txt_studentName);

        Subject subject=new Subject(1,"toan",10,3,4);
        Subject subject1=new Subject(2,"toan",0,3,4);
        subjects.add(subject);
        subjects.add(subject1);

        intent=getIntent();
        String studentName=intent.getStringExtra("student").toString();
        txtStudentName.setText(studentName);

        List<String> spinnerList=new ArrayList<>();
        for (Subject subject2:subjects)
            spinnerList.add(subject2.getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.subjectSpinner.setAdapter(adapter);

        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectPosition=position;
                attendance.setText(subjects.get(position).getAttendancePercent()+"");
                test.setText("8");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalPoint.setText(subjectPosition+"");
                Toast.makeText(AddPointActivity.this, "Add Point successful", Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddPointActivity.this, ManagerSearchStudentActivity.class);
                startActivity(intent);
            }
        });
    }


}