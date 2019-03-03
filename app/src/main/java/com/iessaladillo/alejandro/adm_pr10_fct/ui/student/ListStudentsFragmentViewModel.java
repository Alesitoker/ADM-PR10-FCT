package com.iessaladillo.alejandro.adm_pr10_fct.ui.student;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListStudentsFragmentViewModel extends ViewModel {

    MutableLiveData<List<Student>> liveData = new MutableLiveData<>();

    public ListStudentsFragmentViewModel(Repository repository) {

    }

    public void addStudent() {

    }

    public LiveData<List<Student>> getStudents() {
        return liveData;
    }
}
