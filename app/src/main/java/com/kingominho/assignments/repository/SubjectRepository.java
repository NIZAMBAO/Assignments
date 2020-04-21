package com.kingominho.assignments.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kingominho.assignments.dao.SubjectDao;
import com.kingominho.assignments.database.AssignmentsDatabase;
import com.kingominho.assignments.entity.Subject;

import java.util.List;

public class SubjectRepository {

    private SubjectDao mSubjectDao;
    private LiveData<List<Subject>> mAllSubjects;
    private LiveData<Subject> mSubjectById;

    public SubjectRepository(Application application) {
        AssignmentsDatabase db = AssignmentsDatabase.getDatabase(application);
        mSubjectDao= db.subjectDao();
        mAllSubjects = mSubjectDao.getAll();
    }

    public LiveData<List<Subject>> getAllSubjects() {
        return mAllSubjects;
    }


    /* the following part has been replaced by lambda expression
    new Runnable() {
            @Override
            public void run() {
                mSubjectDao.insert(subject);
            }
        }
     */

    public void insert(Subject subject) {
        AssignmentsDatabase.databaseWriteExecutor.execute(()-> {
            mSubjectDao.insert(subject);
        });
    }


    public void delete(Subject subject) {
        AssignmentsDatabase.databaseWriteExecutor.execute(()-> {
            mSubjectDao.delete(subject);
        });
    }

    public void deleteAll() {
        AssignmentsDatabase.databaseWriteExecutor.execute(()->{
            mSubjectDao.deleteAll();
        });
    }
}
