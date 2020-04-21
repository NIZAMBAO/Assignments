package com.kingominho.assignments.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kingominho.assignments.entity.Assignment;

import java.util.List;

@Dao
public interface AssignmentDao {

    @Insert
    void insert(Assignment assignment);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    @Query("SELECT * FROM assignment_table")
    LiveData<List<Assignment>> getAll();

    @Query("SELECT * FROM assignment_table WHERE is_completed=:isCompleted")
    LiveData<List<Assignment>> getAll(int isCompleted);

    @Query("DELETE FROM assignment_table")
    void deleteAll();


    /*
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);
     */
}
