package com.btntrung.pointmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.btntrung.pointmanagement.DetailPointActivity;
import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.entity.Student;
import com.btntrung.pointmanagement.entity.Subject;

import java.util.List;

public class ManagerSearchStudentAdapter extends RecyclerView.Adapter<ManagerSearchStudentAdapter.ViewHolder> {
    private Context context;
    private List<Student> students;

    public ManagerSearchStudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ManagerSearchStudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_view,parent,false);
        return new ManagerSearchStudentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerSearchStudentAdapter.ViewHolder holder, int position) {
        Student student=students.get(position);
        holder.studentName.setText(student.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailPointActivity.class);
                intent.putExtra("student", student.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        public TextView studentName;

        public ViewHolder(View itemView){
            super(itemView);

            studentName=itemView.findViewById(R.id.txt_item);
        }
    }
}
