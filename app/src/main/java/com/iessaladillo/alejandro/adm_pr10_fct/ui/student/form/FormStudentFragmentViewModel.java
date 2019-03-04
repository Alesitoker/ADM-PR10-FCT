package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class FormStudentFragmentViewModel extends ViewModel {

    Repository repository;

    public FormStudentFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
