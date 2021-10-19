package com.example.jettesapp2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class fragment_aufgaben extends Fragment {

    View view;

    public void setAdapter(MainAdapter adapter) {
        this.adapter = adapter;
    }

    //Mit Jatpack der Versuch
    public static LinearLayoutManager linearLayoutManager;
    public static MainAdapter adapter;

    //Code aus Internet
    private RecyclerView recyclerView;

    //EXPERIMENTELLER BEREICH
    private Context context;
    //EXPERIMENTELLER BEREICH

    public void setContext(Context context){
        this.context = context;
    }

    public fragment_aufgaben(){
        //this.neueFragen = neueFragen;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        linearLayoutManager = new LinearLayoutManager(context);

        view = inflater.inflate(R.layout.fragment_aufgaben, container, false);

        //Hier Code aus Internet hinzugefügt
        try {
            recyclerView = view.findViewById(R.id.recyclerView_aufgaben);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }
}