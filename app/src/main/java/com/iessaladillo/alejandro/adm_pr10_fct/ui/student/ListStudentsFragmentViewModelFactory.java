package com.iessaladillo.alejandro.adm_pr10_fct.ui.student;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListStudentsFragmentViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    public ListStudentsFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ListStudentsFragmentViewModel(repository);
    }
}
