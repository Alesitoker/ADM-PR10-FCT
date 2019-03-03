package com.iessaladillo.alejandro.adm_pr10_fct.ui.student;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListStudentsFragmentViewModel extends ViewModel {

    MutableLiveData<List<Student>> students = new MutableLiveData<>();
    Repository repository;

    public ListStudentsFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void addStudent() {

    }

    public MutableLiveData<List<Student>> getStudents() {
        return students;
    }
}
