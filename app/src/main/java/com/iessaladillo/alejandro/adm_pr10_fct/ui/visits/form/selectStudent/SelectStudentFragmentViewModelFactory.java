package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form.selectStudent;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SelectStudentFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public SelectStudentFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SelectStudentFragmentViewModel(repository);
    }
}
