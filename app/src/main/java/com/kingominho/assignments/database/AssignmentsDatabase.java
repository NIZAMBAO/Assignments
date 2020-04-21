package com.kingominho.assignments.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kingominho.assignments.dao.AssignmentDao;
import com.kingominho.assignments.dao.SubjectDao;
import com.kingominho.assignments.entity.Assignment;
import com.kingominho.assignments.entity.Subject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Assignment.class, Subject.class}, version = 1)
public abstract class AssignmentsDatabase extends RoomDatabase {
    public abstract AssignmentDao assignmentDao();
    public abstract SubjectDao subjectDao();

    private static volatile AssignmentsDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AssignmentsDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (AssignmentsDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AssignmentsDatabase.class, "assignments_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
