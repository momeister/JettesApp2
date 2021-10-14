package com.example.jettesapp2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daprlabs.cardstack.SwipeDeck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<CourseModal> courseModalArrayList;
    public String Test;
    public List<String> Fragen;
    public List<String> Aufgaben;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on below line we are initializing our array list and swipe deck.
        courseModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        Random rn = new Random();
        //Pbergibt die Fragen und Antowrten

        Fragen = new ArrayList<String>();
        Aufgaben = new ArrayList<String>();

        genFesteFragen();
        genFesteAufgaben();

        courseModalArrayList.add(new CourseModal(-1, Test));

        // on below line we are creating a variable for our adapter class and passing array list to it.
        final DeckAdapter adapter = new DeckAdapter(courseModalArrayList, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);



        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                //Toast.makeText(MainActivity.this, "Aufgabe", Toast.LENGTH_SHORT).show();

                //Hier einfügen für neue Karten;
                int Dice = rn.nextInt(Aufgaben.size());
                courseModalArrayList.add(new CourseModal(Dice,Aufgaben.get(Dice)));
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swipped to right we are displaying a toast message.
                //Toast.makeText(MainActivity.this, "Fragen", Toast.LENGTH_SHORT).show();

                //Hier einfügen für neue Karten;

                int Dice = rn.nextInt(Fragen.size());
                courseModalArrayList.add(new CourseModal(0,Fragen.get(Dice)));
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(MainActivity.this, "No more courses present", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void cardActionDown() {
                // this method is called when card is swipped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });

        final Button button = findViewById(R.id.button_ver_u_hin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void genFesteFragen(){
        String tada = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("TextFragen.txt", AssetManager.ACCESS_STREAMING)));
            while ((tada = br.readLine()) != null){
                Fragen.add(tada);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void genFesteAufgaben(){
        String blabla = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("TextAufgaben.txt", AssetManager.ACCESS_STREAMING)));
            while ((blabla = br.readLine()) != null){
                Aufgaben.add(blabla);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
