package com.example.jettesapp2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDaoAufgaben {
    @Insert
    void insert(MainDataAufgaben mainData);

    @Delete
    void delete(MainDataAufgaben mainData);

    @Delete
    void reset(List<MainDataAufgaben> mainDataList);

    @Query("UPDATE table_name_aufgaben SET aufgaben = :sText WHERE ID = :sID")
    void update(int sID, String sText);

    //  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT aufgaben, ID FROM table_name_aufgaben")
    List<MainData> getAll();

    // @Query("SELECT aufgaben, ID FROM table_name")
    // List<MainData> getAllAufgaben();

}
