package com.cns.bangladeshrailway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList();
//        arrayList.add(new TrainItem("360", "215", "511", "55"));
//        arrayList.add(new TrainItem("500", "115", "611", "25"));
//        arrayList.add(new TrainItem("260", "415", "911", "55"));
//        arrayList.add(new TrainItem("460", "715", "811", "75"));
//        arrayList.add(new TrainItem("160", "515", "711", "85"));


//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, HomeActivity.this);
//        recyclerView.setAdapter(adapter);
//
//        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);

    }

//    @Override
//    public void onItemClick(TrainItem item) {
//        Toast.makeText(getApplicationContext(), item.getTotalTrainNumber() + " is clicked", Toast.LENGTH_SHORT).show();
//    }
}