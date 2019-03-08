package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.list;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ListStudentsFragmentViewModel extends ViewModel {

    private final LiveData<List<StudentCompany>> students;

    public ListStudentsFragmentViewModel(Repository repository) {
        students = repository.queryStudents();
    }

    public LiveData<List<StudentCompany>> getStudents() {
        return students;
    }
}
