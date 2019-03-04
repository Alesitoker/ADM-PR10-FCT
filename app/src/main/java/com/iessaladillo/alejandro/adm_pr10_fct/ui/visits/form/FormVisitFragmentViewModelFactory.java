package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FormVisitFragmentViewModelFactory implements ViewModelProvider.Factory {

    Repository repository;

    public FormVisitFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FormVisitFragmentViewModel(repository);
    }
}
