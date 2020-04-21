package com.kingominho.assignments.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kingominho.assignments.entity.Subject;
import com.kingominho.assignments.repository.SubjectRepository;

import java.util.List;

public class SubjectsViewModel extends AndroidViewModel {

    private SubjectRepository mSubjectRepository;

    private LiveData<List<Subject>> mAllSubjects;

    public SubjectsViewModel(@NonNull Application application) {
        super(application);
        mSubjectRepository = new SubjectRepository(application);
        mAllSubjects = mSubjectRepository.getAllSubjects();
    }

    public LiveData<List<Subject>> getAllSubjects() {
        return mAllSubjects;
    }

    public SubjectRepository getSubjectRepository() {
        return mSubjectRepository;
    }

    public Subject getSubjectById(int id) {
        if (mAllSubjects.getValue() != null) {
            for (Subject s : mAllSubjects.getValue()) {
                if (s.getId() == id) {
                    return s;
                }
            }
        }
        return null;
    }

    public void insert(Subject subject) {
        mSubjectRepository.insert(subject);
    }

    public void delete(Subject subject) {
        mSubjectRepository.delete(subject);
    }

    public void deleteAll() {
        mSubjectRepository.deleteAll();
    }
}
