package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListVisitsFragmentViewModel extends ViewModel {

    MutableLiveData<List<Visit>> visits = new MutableLiveData<>();
    Repository repository;

    public ListVisitsFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Visit>> getVisits() {
        return visits;
    }
}
