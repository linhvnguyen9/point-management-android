package com.btntrung.pointmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.btntrung.pointmanagement.AddPointActivity;
import com.btntrung.pointmanagement.DetailPointActivity;
import com.btntrung.pointmanagement.ManagerViewPointActivity;
import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class ManagerViewPointAdapter extends RecyclerView.Adapter<ManagerViewPointAdapter.ViewHolder> {
    private Context context;
    private Intent intent;

    private List<Subject> subjects=new ArrayList<>();

    public ManagerViewPointAdapter(Context context, List<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public ManagerViewPointAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_view_point,parent,false);
        return new ManagerViewPointAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerViewPointAdapter.ViewHolder holder, int position) {
        Subject subject=subjects.get(position);
        holder.subjectName.setText(subject.getName());
        holder.pointAvg.setText(subject.getFinalPercent()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AddPointActivity.class);
                intent.putExtra("subject", subject.getName());
                intent.putExtra("student", "Duc anh");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        public TextView subjectName;
        public TextView pointAvg;

        public ViewHolder(View itemView){
            super(itemView);

            subjectName=itemView.findViewById(R.id.subjectName);
            pointAvg=itemView.findViewById(R.id.pointAVG);
        }
    }
}
