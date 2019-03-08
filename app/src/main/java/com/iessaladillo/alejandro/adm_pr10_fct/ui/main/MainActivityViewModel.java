package com.iessaladillo.alejandro.adm_pr10_fct.ui.main;

import com.iessaladillo.alejandro.adm_pr10_fct.base.Event;
import com.iessaladillo.alejandro.adm_pr10_fct.base.TransferSelect;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<Event<TransferSelect>> transferred = new MutableLiveData<>();

    public void setTransferred(TransferSelect transferSelect) {
        transferred.setValue(new Event<>(transferSelect));
    }

    public LiveData<Event<TransferSelect>> getTransferred() {
        return transferred;
    }
}
