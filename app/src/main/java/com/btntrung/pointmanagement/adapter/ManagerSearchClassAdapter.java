package com.btntrung.pointmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.btntrung.pointmanagement.DetailPointActivity;
import com.btntrung.pointmanagement.ManagerSearchStudentActivity;
import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.entity.ClassRoom;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagerSearchClassAdapter extends
        RecyclerView.Adapter<ManagerSearchClassAdapter.ViewHolder>{
    private Context context;
    private List<ClassRoom> classRooms;


    public ManagerSearchClassAdapter(Context context, List<ClassRoom> classRooms) {
        this.context = context;
        this.classRooms = classRooms;
    }

    @NonNull
    @Override
    public ManagerSearchClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_text_view,parent,false);
        return new ManagerSearchClassAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
        ClassRoom classRoom=classRooms.get(position);

        holder.className.setText(classRoom.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ManagerSearchStudentActivity.class);
                intent.putExtra("class", classRoom.getName());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
            return classRooms.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView className;

        public ViewHolder(@NonNull View v) {
            super(v);
            className=v.findViewById(R.id.txt_item);
        }
    }
}
