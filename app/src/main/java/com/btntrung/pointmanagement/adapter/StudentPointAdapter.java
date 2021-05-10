package com.btntrung.pointmanagement.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class StudentPointAdapter extends RecyclerView.Adapter<StudentPointAdapter.ViewHolder>{
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentPointAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class  ViewHolder extends  RecyclerView.ViewHolder{

        public ViewHolder(View itemView){
            super(itemView);

        }
    }
}
