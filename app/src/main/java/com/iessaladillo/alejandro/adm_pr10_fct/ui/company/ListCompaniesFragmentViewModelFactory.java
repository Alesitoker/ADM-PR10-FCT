package com.iessaladillo.alejandro.adm_pr10_fct.ui.company;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListCompaniesFragmentViewModelFactory implements ViewModelProvider.Factory {

    Repository repository;

    public ListCompaniesFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListCompaniesFragmentViewModel(repository);
    }
}
