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
import com.btntrung.pointmanagement.presentation.student.model.StudentPointModel;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class StudentPointAdapter extends RecyclerView.Adapter<StudentPointAdapter.ViewHolder>{
    private Context context;
    private List<StudentPointModel> listPoint;

    public StudentPointAdapter(Context context, List<StudentPointModel> listPoint) {
        this.context = context;
        this.listPoint = listPoint;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_student_subject,parent,false);
        return new StudentPointAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentPointAdapter.ViewHolder holder, int position) {
        StudentPointModel point=listPoint.get(position);
        holder.subjectName.setText(point.getSubject().getName().toString());
        holder.point.setText(point.getAvg()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailPointActivity.class);
                intent.putExtra("point",(Serializable) point);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listPoint.size();
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
