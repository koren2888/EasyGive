package com.example.easygive.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {
    @Query("select * from Item")
    List<Item> getAll();

    @Query("select * from Item where id = :studentId")
    Item getStudentById(String studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Item... students);

    @Delete
    void delete(Item student);
}
