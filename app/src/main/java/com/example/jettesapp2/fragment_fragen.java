package com.example.jettesapp2;

import static android.content.Context.APPWIDGET_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class fragment_fragen extends Fragment {

    View view;
    RandomNumListAdapter adapter1;

    protected List<String> neueFragen;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    //Mit Jatpack der Versuch
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    //Code aus Internet
    private RecyclerView recyclerView;

    public fragment_fragen(List<String> neueFragen, LinearLayoutManager linearLayoutManager, MainAdapter adapter){
        this.neueFragen = neueFragen;
        this.linearLayoutManager = linearLayoutManager;
        this.adapter = adapter;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //neueFragen = new ArrayList<String>();
        neueFragen();
        view = inflater.inflate(R.layout.fragment_fragen, container, false);

        //Hier Code aus Internet hinzugefügt
        try {
            recyclerView = view.findViewById(R.id.recyclerView_fragen);
            /*
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            adapter1 = new RandomNumListAdapter(neueFragen);
            recyclerView.setAdapter(adapter1);

             */



            //database = RoomDB.getInstance(getContext());
            //dataList = database.mainDao().getAll();

            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        }catch (Exception e){

        }


        return view;
    }

    //Gibt Inhalt Textdatei aus
    private void neueFragen() {
        String filename = "TexteFragen.txt";
        String tada = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getContext().openFileInput(filename)));
            while ((tada = br.readLine()) != null){
                neueFragen.add(tada);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    /*    Alte (Im Asset Ordner funktionierende) Version
        String tada = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("NeueFragen.txt", AssetManager.ACCESS_STREAMING)));
            while ((tada = br.readLine()) != null){
                neueFragen.add(tada);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    */

    }
    //soll aus Dialog Inhalt in Textdatei einspeichern
    //!!!!! Funktioniert noch nicht !!!!!!!
    //Wahrscheinlicher Grund : Inhalt Textdatei wird überspeichert -> nicht in neue Zeile geschrieben
    public void einschreiben(String eingabe){
        String filename = "TexteFragen.txt";
        FileOutputStream outputStream;

        try {
            outputStream = getContext().openFileOutput(filename,getContext().MODE_PRIVATE);
            outputStream.write(eingabe.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}