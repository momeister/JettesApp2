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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class fragment_fragen extends Fragment{

    View view;
    protected List<String> neueFragen;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        neueFragen = new ArrayList<String>();
        neueFragen();

        view = inflater.inflate(R.layout.fragment_fragen, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragen);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter(this.getContext(), neueFragen);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    private void neueFragen() {
        String tada = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("NeueFragen.txt", AssetManager.ACCESS_STREAMING)));
            while ((tada = br.readLine()) != null){
                neueFragen.add(tada);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}