package com.iessaladillo.alejandro.adm_pr10_fct.ui.company;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListCompaniesFragmentViewModel extends ViewModel {

    MutableLiveData<List<Company>> companies = new MutableLiveData<>();
    Repository repository;

    public ListCompaniesFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Company>> getCompanies() {
        return companies;
    }
}
