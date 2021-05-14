package com.btntrung.pointmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.btntrung.pointmanagement.presentation.student.StudentMainActivity;
import com.btntrung.pointmanagement.presentation.student.model.StudentPointModel;

public class DetailPointActivity extends AppCompatActivity {
    private Button btnBack;
    private Intent intent;
    private TextView attendance,test,project,finalPoint,avg,subjectName, semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_point);

        btnBack=findViewById(R.id.btn_back);
        attendance=findViewById(R.id.txt_attandancePoint);
        test=findViewById(R.id.txt_testPoint);
        project=findViewById(R.id.txt_projectPoint);
        finalPoint=findViewById(R.id.txt_finalPoint);
        avg=findViewById(R.id.txt_avgPoint);
        subjectName=findViewById(R.id.txt_subject);
        semester=findViewById(R.id.txt_description);


        intent=getIntent();
        StudentPointModel point=(StudentPointModel)intent.getSerializableExtra("point");

        subjectName.setText(point.getSubject().getName());
        semester.setText(point.getSemester().getName());
        attendance.setText(point.getAttendance()+"");
        test.setText(point.getTest()+"");
        project.setText(point.getProject()+"");
        finalPoint.setText(point.getFinalPoint()+"");
        avg.setText(point.getAvg()+"");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailPointActivity.this, StudentMainActivity.class));
            }
        });
    }
}