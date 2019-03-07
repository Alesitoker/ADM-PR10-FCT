package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.list;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListStudentsFragmentViewModel extends ViewModel {

    MutableLiveData<List<StudentCompany>> students = new MutableLiveData<>();
    Repository repository;

    public ListStudentsFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<StudentCompany>> getStudents() {
        return students;
    }
}
