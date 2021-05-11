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
import com.btntrung.pointmanagement.entity.Subject;

import java.util.List;

public class StudentSubjectAdapter extends RecyclerView.Adapter<StudentSubjectAdapter.ViewHolder> {
    private Context context;
    private List<Subject> subjects;

    public StudentSubjectAdapter(Context context, List<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_student_subject,parent,false);
        return new StudentSubjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subject subject=subjects.get(position);
        holder.subjectName.setText(subject.getName());
        holder.point.setText("9");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailPointActivity.class);
                intent.putExtra("subject", subject.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        public TextView subjectName,point;

        public ViewHolder(View itemView){
            super(itemView);
            subjectName=itemView.findViewById(R.id.txt_subjectName);
            point=itemView.findViewById(R.id.txt_point);
        }
    }
}
