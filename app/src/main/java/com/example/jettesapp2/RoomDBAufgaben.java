package com.example.jettesapp2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDBAufgaben extends RoomDatabase {

    private static RoomDBAufgaben databaseaufgaben;

    private static String DATABASE_NAME = "database";

    public synchronized static RoomDBAufgaben getInstance(Context context){
        if(databaseaufgaben == null){
            databaseaufgaben = Room.databaseBuilder(context.getApplicationContext(),RoomDBAufgaben.class,DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return databaseaufgaben;
    }

    public abstract MainDao mainDao();
}

