package com.kingominho.assignments.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kingominho.assignments.entity.Subject;

import java.util.List;

@Dao
public interface SubjectDao {

    @Insert
    void insert(Subject subject);

    @Delete
    void delete(Subject subject);

    @Query("SELECT * FROM subjects_table")
    LiveData<List<Subject>> getAll();


    @Query("DELETE FROM subjects_table")
    void deleteAll();
}
