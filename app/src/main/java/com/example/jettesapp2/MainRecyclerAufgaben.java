package com.example.jettesapp2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAufgaben extends AppCompatActivity implements MyRecyclerViewAdapterAufgaben.ItemClickListener {

    protected List<String> neueAufgabe;

    EditText editText;

    Button btAdd, btFragen;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycle_aufgabe);

        editText = findViewById(R.id.edit_text);
        btAdd = findViewById(R.id.bt_add);
        btFragen = findViewById(R.id.bt_fragen);
        recyclerView = findViewById(R.id.recycler_view);

        /*
        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(MainRecyclerAufgaben.this,dataList);
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sText = editText.getText().toString().trim();

                if(!sText.equals("")){
                    MainData data = new MainData();
                    data.setText(sText);
                    database.mainDao().insert(data);
                    editText.setText("");

                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btFragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityFragen();
            }
        });

         */

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public void ActivityFragen(){
        Intent intent = new Intent(this, MainRecyclerFragen.class);
        startActivity(intent);
    }
}