package com.example.jettesapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAufgabenn extends AppCompatActivity implements MyRecyclerViewAdapterAufgaben.ItemClickListener{

    MyRecyclerViewAdapterAufgaben adapter;
    protected List<String> neueAufgabe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycle_aufgabe);

        // data to populate the RecyclerView with
        neueAufgabe = new ArrayList<String>();
        neueAufgabe();

        final Button button_zur = findViewById(R.id.button_zur);
        button_zur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        final Button button_zu_aufg = findViewById(R.id.button_zu_aufgaben);
        button_zu_aufg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        final Button button_zu_fragen = findViewById(R.id.button_zu_fragen);
        button_zu_fragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRecyclerFragen();
            }
        });

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimalss);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapterAufgaben(this, neueAufgabe);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
/*
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Snackbar.make(view, "Here ist a Snakebar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

 */

    }

    private void openActivityRecyclerFragen() {
        Intent intent = new Intent(this, MainRecyclerFragen.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    private void neueAufgabe(){
        String tada = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("NeueAufgabe.txt", AssetManager.ACCESS_STREAMING)));
            while ((tada = br.readLine()) != null){
                neueAufgabe.add(tada);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void openActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}