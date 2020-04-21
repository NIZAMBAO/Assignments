package com.kingominho.assignments.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kingominho.assignments.dao.AssignmentDao;
import com.kingominho.assignments.database.AssignmentsDatabase;
import com.kingominho.assignments.entity.Assignment;

import java.util.List;

public class AssignmentsRepository {

    private AssignmentDao mAssignmentDao;
    private LiveData<List<Assignment>> mAllCompletedAssignments;
    private LiveData<List<Assignment>> mAllRemainingAssignments;
    private  LiveData<List<Assignment>> mAllAssignments;

    public AssignmentsRepository(Application application) {
        AssignmentsDatabase db = AssignmentsDatabase.getDatabase(application);
        mAssignmentDao = db.assignmentDao();
        mAllAssignments = mAssignmentDao.getAll();
        mAllCompletedAssignments = mAssignmentDao.getAll(1);
        mAllRemainingAssignments = mAssignmentDao.getAll(0);
    }

    public LiveData<List<Assignment>> getAllCompletedAssignments() {
        return mAllCompletedAssignments;
    }

    public LiveData<List<Assignment>> getAllRemainingAssignments() {
        return mAllRemainingAssignments;
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return mAllAssignments;
    }

    /* the following part has been replaced by lambda expression
    new Runnable() {
            @Override
            public void run() {
                mAssignmentDao.insert(assignment);
            }
        }
     */

    public void insert(Assignment assignment) {
        AssignmentsDatabase.databaseWriteExecutor.execute(()-> {
            mAssignmentDao.insert(assignment);
        });
    }

    public void update(Assignment assignment) {
        AssignmentsDatabase.databaseWriteExecutor.execute(()-> {
            mAssignmentDao.update(assignment);
        });
    }

    public void delete(Assignment assignment) {
        AssignmentsDatabase.databaseWriteExecutor.execute(()-> {
            mAssignmentDao.delete(assignment);
        });
    }

    public void deleteAll() {
        AssignmentsDatabase.databaseWriteExecutor.execute(()->{
            mAssignmentDao.deleteAll();
        });
    }
}
