package com.btntrung.pointmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.btntrung.pointmanagement.adapter.StudentSubjectAdapter;
import com.btntrung.pointmanagement.entity.Student;

public class DetailPointActivity extends AppCompatActivity {
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_point);

        btnBack=findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailPointActivity.this, StudentMainActivity.class));
            }
        });
    }
}