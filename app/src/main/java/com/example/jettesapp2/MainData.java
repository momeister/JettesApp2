package com.example.jettesapp2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "text")
    private String text;
//EPREIMENTELLER BEREICH
   // @ColumnInfo(name = "aufgaben")
    //private String aufgaben;
    //EPREIMENTELLER BEREICH
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //EPREIMENTELLER BEREICH
/*
    public String getAufgaben(){
        return aufgaben;
    }

    public void setAufgaben(String aufgaben){
        this.aufgaben = aufgaben;
    }

 */
    //EPREIMENTELLER BEREICH
}
