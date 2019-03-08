package com.iessaladillo.alejandro.adm_pr10_fct.ui.nextVisits;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NextVisitsFragmentViewModel extends ViewModel {

    private final LiveData<List<VisitStudent>> visits;

    public NextVisitsFragmentViewModel(Repository repository) {
        visits = repository.queryNextVisits();
    }

    public LiveData<List<VisitStudent>> getVisits() {
        return visits;
    }
}
