package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form.selectStudent;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SelectStudentFragmentViewModel extends ViewModel {

    private final LiveData<List<StudentCompany>> visits;
    private final Repository repository;

    public SelectStudentFragmentViewModel(Repository repository) {
        this.repository = repository;

        visits = repository.queryStudents();
    }

    public LiveData<List<StudentCompany>> getVisits() {
        return visits;
    }
}
