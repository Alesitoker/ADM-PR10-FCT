package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form.selectCompany;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.company.form.FormCompanyFragmentViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SelectCompanyFragmentViewModelFactory implements ViewModelProvider.Factory {

    Repository repository;

    public SelectCompanyFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FormCompanyFragmentViewModel(repository);
    }
}
