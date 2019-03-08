package com.iessaladillo.alejandro.adm_pr10_fct.ui.nextVisits;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NextVisitsFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public NextVisitsFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NextVisitsFragmentViewModel(repository);
    }
}
