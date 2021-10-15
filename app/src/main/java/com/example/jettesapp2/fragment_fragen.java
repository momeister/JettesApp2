package com.example.jettesapp2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class fragment_fragen extends Fragment {

    View view;

    //protected List<String> neueFragen;

    public void setLinearLayoutManager(final LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

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

    public fragment_fragen(){
        //this.neueFragen = neueFragen;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //neueFragen = new ArrayList<String>();
        //neueFragen();

        //EXPERIMENTELLER BEREICH
        linearLayoutManager = new LinearLayoutManager(context);
        //EXPERIMENTELLER BEREICH


        view = inflater.inflate(R.layout.fragment_fragen, container, false);

        //Hier Code aus Internet hinzugefügt
        try {
            recyclerView = view.findViewById(R.id.recyclerView_fragen);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
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
           //     neueFragen.add(tada);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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