package com.example.jettesapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class Activity2 extends AppCompatActivity {

    ToggleButton FragAufg, HinzLoes;
    private boolean Frage0Aufg1, Hinzu0Loeschen1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        FragAufg = (ToggleButton) findViewById(R.id.toggleButton_frag_aufg);
        HinzLoes = (ToggleButton) findViewById(R.id.toggleButton_hin_loes);

        FragAufg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Frage0Aufg1 = b;
            }
        });

        HinzLoes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Hinzu0Loeschen1 = b;
            }
        });

        final Button button_zur = findViewById(R.id.button_zur);
        button_zur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        final Button button_best = findViewById(R.id.button_best);
        button_best.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Frage0Aufg1 == false && Hinzu0Loeschen1 == false){
                    openOptions();
                }else if (Frage0Aufg1 == true && Hinzu0Loeschen1 == false){
                    openHinzFrage();
                }else if (Frage0Aufg1 == false && Hinzu0Loeschen1 == true){
                    openOptions();;
                }else{
                    openHinzFrage();
                }
            }
        });

    }

    public void openActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openHinzFrage(){
        Intent intent = new Intent(this, MainRecyclerFragen.class);
        startActivity(intent);
    }

    public void openOptions(){
        Intent intent = new Intent(this, Activity_Option.class);
        startActivity(intent);
    }
}