package com.example.jettesapp2;

import static android.content.Context.APPWIDGET_SERVICE;

import android.annotation.SuppressLint;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class fragment_fragen extends Fragment{

    View view;
    RandomNumListAdapter adapter;

    protected List<String> neueFragen;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    //Code aus Internet
    private RecyclerView recyclerView;

    public fragment_fragen(List<String> neueFragen){
        this.neueFragen = neueFragen;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        neueFragen = new ArrayList<String>();
        neueFragen();
        view = inflater.inflate(R.layout.fragment_fragen, container, false);

        //Hier Code aus Internet hinzugefügt
        try {
            recyclerView = view.findViewById(R.id.recyclerView_fragen);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            adapter = new RandomNumListAdapter(neueFragen);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){

        }


        return view;
    }

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

}