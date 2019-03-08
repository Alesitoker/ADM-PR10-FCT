package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.list;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListVisitsFragmentViewModel extends ViewModel {

    LiveData<List<VisitStudent>> visits;

    public ListVisitsFragmentViewModel(Repository repository) {
        visits = repository.queryVisits();
    }

    public LiveData<List<VisitStudent>> getVisits() {
        return visits;
    }
}
