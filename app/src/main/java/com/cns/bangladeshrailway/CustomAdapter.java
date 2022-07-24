package com.cns.bangladeshrailway;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class CustomAdapter{
//    List<TrainItem> studentDataList;
//    public CustomAdapter(List<TrainItem> studentDataList) {
//        this.studentDataList = studentDataList;
//    }
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.item_list, viewGroup, false);
//        return new MyViewHolder(itemView);
//    }
//    @Override
//    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
//        TrainItem data=studentDataList.get(i);
//        Random rnd = new Random();
//        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//        viewHolder.parent.setBackgroundColor(currentColor);
//        viewHolder.name.setText(data.getTotalTrainNumber());
//        viewHolder.age.setText(String.valueOf(data.getAntoNagarTrainNumber()));
//    }
//    @Override
//    public int getItemCount() {
//        return studentDataList.size();
//    }
//    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView name,age;
//        LinearLayout parent;
//        public MyViewHolder(View itemView) {
//            super(itemView);
////            parent = itemView.findViewById(R.id.parent);
////            name = itemView.findViewById(R.id.name);
////            age = itemView.findViewById(R.id.age);
//        }
//    }
}