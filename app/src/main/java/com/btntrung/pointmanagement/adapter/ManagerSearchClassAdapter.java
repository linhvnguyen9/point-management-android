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
import com.btntrung.pointmanagement.R;
import com.btntrung.pointmanagement.entity.ClassRoom;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ManagerSearchClassAdapter extends
        RecyclerView.Adapter<ManagerSearchClassAdapter.ClassViewHolder>{
    private List<ClassRoom> classRooms;
    private Context context;

    public ManagerSearchClassAdapter(List<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.item_text_view,parent,false);
        return new ClassViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder,
                                 int position) {
        ClassRoom classRoom=classRooms.get(position);

        holder.className.setText(classRoom.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailPointActivity.class);
                intent.putExtra("class", classRoom.getId());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
            return classRooms.size();
    }
    class ClassViewHolder extends RecyclerView.ViewHolder{
        private TextView className;

        public ClassViewHolder(@NonNull View v) {
            super(v);
            className=v.findViewById(R.id.txt_item);
        }
    }
}
