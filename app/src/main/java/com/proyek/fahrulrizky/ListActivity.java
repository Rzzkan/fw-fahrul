package com.proyek.fahrulrizky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.proyek.fahrulrizky.adapter.AdapterList;
import com.proyek.fahrulrizky.adapter.PresensiModel;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    RecyclerView rvList;
    AdapterList adapter;
    ArrayList<PresensiModel> items;
    SQLiteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initialization();
    }

    private void initialization(){
        getSupportActionBar().setTitle("List Presensi");
        dbHelper = new SQLiteDBHelper(this);
        rvList = findViewById(R.id.rvList);
        items = new ArrayList<>();
        items = dbHelper.getData();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
        adapter = new AdapterList(this, items);
        rvList.setAdapter(adapter);
    }
}