package com.btntrung.pointmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.btntrung.pointmanagement.presentation.student.StudentMainActivity;
import com.btntrung.pointmanagement.presentation.student.model.StudentPointModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailPointActivity extends AppCompatActivity {
    private Button btnBack;
    private Intent intent;
    private TextView attendance,test,project,finalPoint,avg,subjectName, semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_point);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnBack=findViewById(R.id.btn_back);
        attendance=findViewById(R.id.txt_attandancePoint);
        test=findViewById(R.id.txt_testPoint);
        project=findViewById(R.id.txt_projectPoint);
        finalPoint=findViewById(R.id.txt_finalPoint);
        avg=findViewById(R.id.txt_avgPoint);
        subjectName=findViewById(R.id.txt_subject);
        semester=findViewById(R.id.txt_description);
//        profileImage=findViewById(R.id.profile_image);
//        username=findViewById(R.id.username);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            actionBar.setTitle(name);

            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setLogo();
            actionBar.setDisplayUseLogoEnabled(true);
        }


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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}