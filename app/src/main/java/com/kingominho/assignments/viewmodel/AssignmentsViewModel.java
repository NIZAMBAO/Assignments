package com.kingominho.assignments.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kingominho.assignments.entity.Assignment;
import com.kingominho.assignments.repository.AssignmentsRepository;

import java.util.List;

public class AssignmentsViewModel extends AndroidViewModel {

    private AssignmentsRepository mAssignmentsRepository;
    private LiveData<List<Assignment>> mAllCompletedAssignments;
    private LiveData<List<Assignment>> mAllRemainingAssignments;
    private LiveData<List<Assignment>> mAllAssignments;

    public AssignmentsViewModel(@NonNull Application application) {
        super(application);
        mAssignmentsRepository = new AssignmentsRepository(application);
        mAllCompletedAssignments = mAssignmentsRepository.getAllCompletedAssignments();
        mAllRemainingAssignments = mAssignmentsRepository.getAllRemainingAssignments();
        mAllAssignments = mAssignmentsRepository.getAllAssignments();
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

    public void insert(Assignment assignment) {
        mAssignmentsRepository.insert(assignment);
    }

    public void update(Assignment assignment) {
        mAssignmentsRepository.update(assignment);
    }

    public void delete(Assignment assignment) {
        mAssignmentsRepository.delete(assignment);
    }

    public void deleteAll() {
        mAssignmentsRepository.deleteAll();
    }
}
