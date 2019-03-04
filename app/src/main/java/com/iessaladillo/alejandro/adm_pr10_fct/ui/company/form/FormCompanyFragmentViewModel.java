package com.iessaladillo.alejandro.adm_pr10_fct.ui.company.form;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class FormCompanyFragmentViewModel extends ViewModel {

    Repository repository;

    public FormCompanyFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
