package com.example.jettesapp2;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

@Dao
public interface MainDao {
    @Insert
    void insert(MainData mainData);

    @Delete
    void delete(MainData mainData);

    @Delete
    void reset(List<MainData> mainDataList);

    @Query("UPDATE table_name SET text = :sText WHERE ID = :sID")
    void update(int sID, String sText);

  //  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT text, ID FROM table_name")
    List<MainData> getAll();

   // @Query("SELECT aufgaben, ID FROM table_name")
   // List<MainData> getAllAufgaben();

}
