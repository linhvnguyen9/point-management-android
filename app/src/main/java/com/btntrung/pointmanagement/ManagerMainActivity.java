package com.btntrung.pointmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.btntrung.pointmanagement.adapter.ManagerSearchClassAdapter;
import com.btntrung.pointmanagement.entity.ClassRoom;

import java.util.ArrayList;
import java.util.List;

public class ManagerMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ManagerSearchClassAdapter classAdapter;
    private List<ClassRoom> classRooms=new ArrayList<>();
    private Button btn_search;
    private TextView textView;
    private EditText key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        recyclerView=findViewById(R.id.recycler_view);
        btn_search=findViewById(R.id.btn_search);
        key=findViewById(R.id.edit_search);
////        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ClassRoom classRoom=new ClassRoom(1,"e17cn02");
        ClassRoom classRoom1=new ClassRoom(2,"e17cn03");

        classRooms.add(classRoom);
        classRooms.add(classRoom1);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=key.getText().toString();

                classAdapter=new ManagerSearchClassAdapter(classRooms);
                recyclerView.setAdapter(classAdapter);
            }
        });
    }
}