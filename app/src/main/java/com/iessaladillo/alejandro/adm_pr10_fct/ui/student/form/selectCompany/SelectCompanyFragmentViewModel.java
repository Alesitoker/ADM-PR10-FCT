package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form.selectCompany;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SelectCompanyFragmentViewModel extends ViewModel {

    private LiveData<List<Company>> companies;

    public SelectCompanyFragmentViewModel(Repository repository) {
        companies = repository.queryCompanies();
    }

    public LiveData<List<Company>> getCompanies() {
        return companies;
    }
}
