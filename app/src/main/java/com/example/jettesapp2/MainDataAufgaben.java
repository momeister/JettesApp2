package com.example.jettesapp2;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name_aufgaben")
public class MainDataAufgaben implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "aufgaben")
    private String aufgaben;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAufgaben() {
        return aufgaben;
    }

    public void setAufgaben(String aufgaben) {
        this.aufgaben = aufgaben;
    }
}
