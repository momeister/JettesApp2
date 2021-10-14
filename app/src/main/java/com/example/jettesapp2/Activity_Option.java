package com.example.jettesapp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Activity_Option extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener , Dialog.ExampleDialogListener{

    Button but_fragen, but_aufgaben, but_hinzufuegen;
    EditText editText;

    RoomDB database;
    MainAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<MainData> dataList = new ArrayList<>();


    protected List<String> neueFragen;
    private fragment_fragen fragmentFragen;

    ArrayList<File> dateinliste;
    ArrayList<String> texteliste;

    SharedPreferences sharedPreferences;

    @Override
    public void applyTexts(String eingabe) {
        neueFragen.add(eingabe);
        //einschreiben(this, eingabe);
        //neueFragen(this);
        einschreiben(this, eingabe);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        but_fragen = findViewById(R.id.button_fragen);
        but_aufgaben = findViewById(R.id.button_aufgaben);
        but_hinzufuegen = findViewById(R.id.button_hinzu);
        editText = findViewById(R.id.edit_text);

        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();


        //EXPERIMENTELLER BEREICH
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MainAdapter(Activity_Option.this, dataList);
        //EXPERIMENTELLER BEREICH

        neueFragen = new ArrayList<String>();

        replaceFragment(new fragment_fragen((ArrayList<String>) neueFragen, linearLayoutManager, adapter));
        //replaceFragment(fragmentFragen = new fragment_fragen((ArrayList<String>) neueFragen));

        but_fragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new fragment_fragen((ArrayList<String>) neueFragen, linearLayoutManager, adapter));

            }
        });

        but_aufgaben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new fragment_aufgaben());

            }
        });

        but_hinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        but_hinzufuegen.setOnClickListener(new View.OnClickListener() {
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
        //Heute

        android.app.Dialog dialog = new android.app.Dialog(this);
        dialog.setContentView(R.layout.dialog_update);

        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width,height);

        dialog.show();

        EditText editText = dialog.findViewById(R.id.edit_text);
        Button btUpdate = dialog.findViewById(R.id.bt_update);


        //Damals
       // Dialog exampleDialog = new Dialog();
       // exampleDialog.show(getSupportFragmentManager(), "example dialog");
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

    public void einschreiben2(Context context, String eingabe) throws IOException {
        String filename = "TexteFragen.txt";

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
            bufferedWriter.write(eingabe);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
        }

    }

}