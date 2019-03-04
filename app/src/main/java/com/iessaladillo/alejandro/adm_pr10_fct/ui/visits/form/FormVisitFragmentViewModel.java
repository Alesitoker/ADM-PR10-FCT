package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class FormVisitFragmentViewModel extends ViewModel {

    Repository repository;

    public FormVisitFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
