package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FormStudentFragmentViewModelFactory implements ViewModelProvider.Factory {

    Repository repository;

    public FormStudentFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FormStudentFragmentViewModel(repository);
    }
}
