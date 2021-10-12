package com.example.jettesapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Activity_Option extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener , Dialog.ExampleDialogListener{

    Button but_fragen, but_aufgaben, but_zuruek, but_hinzufuegen;
    protected List<String> neueFragen;
    private fragment_fragen fragmentFragen;

    @Override
    public void applyTexts(String eingabe) {
        neueFragen.add(eingabe);
        //einschreiben(this, eingabe);
        //neueFragen(this);
        fragmentFragen.einschreiben(eingabe);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        but_fragen = findViewById(R.id.button_fragen);
        but_aufgaben = findViewById(R.id.button_aufgaben);
        but_zuruek = findViewById(R.id.button_zurueck);
        but_hinzufuegen = findViewById(R.id.button_hinzufuegen);

        neueFragen = new ArrayList<String>();

        replaceFragment(fragmentFragen = new fragment_fragen((ArrayList<String>) neueFragen));

        but_fragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new fragment_fragen((ArrayList<String>) neueFragen));

            }
        });

        but_aufgaben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new fragment_aufgaben());

            }
        });

        but_zuruek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        but_hinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    public void openActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    private void neueFragen(Context context){
        String filename = "TexteFragen.txt";
        String tada = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(filename)));
            while ((tada = br.readLine()) != null){
                neueFragen.add(tada);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //Funktioniert aber im Asset Ordner
/*
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

    public void openDialog(){
        Dialog exampleDialog = new Dialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }


    //soll aus Dialog Inhalt in Textdatei einspeichern
    //!!!!! Funktioniert noch nicht !!!!!!!
    //Wahrscheinlicher Grund : Inhalt Textdatei wird überspeichert -> nicht in neue Zeile geschrieben
    public void einschreiben(Context context, String eingabe){
        String filename = "TexteFragen.txt";
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, context.MODE_PRIVATE);
            outputStream.write(eingabe.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


/*
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

 */

    }
}