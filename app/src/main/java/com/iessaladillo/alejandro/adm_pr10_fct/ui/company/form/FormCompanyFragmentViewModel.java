package com.iessaladillo.alejandro.adm_pr10_fct.ui.company.form;


import com.iessaladillo.alejandro.adm_pr10_fct.base.Event;
import com.iessaladillo.alejandro.adm_pr10_fct.base.Resource;
import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class FormCompanyFragmentViewModel extends ViewModel {

    private final MutableLiveData<Company> insertTrigger = new MutableLiveData<>();
    private final MutableLiveData<Company> updateTrigger = new MutableLiveData<>();
    private final MutableLiveData<Company> deleteTrigger = new MutableLiveData<>();
    private final MediatorLiveData<Event<String>> successMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> errorMessage = new MediatorLiveData<>();
    private final LiveData<Resource<Long>> insertResult;
    private final LiveData<Resource<Integer>> updateResult;
    private final LiveData<Resource<Integer>> deleteResult;
    private Repository repository;

    public FormCompanyFragmentViewModel(Repository repository) {
        this.repository = repository;

        insertResult = Transformations.switchMap(insertTrigger, repository::insertCompany);
        updateResult = Transformations.switchMap(updateTrigger, repository::updateCompany);
        deleteResult = Transformations.switchMap(deleteTrigger, repository::updateCompany);

        setupSuccesMessage();
        setupErrorMessage();
    }

    private void setupSuccesMessage() {
        successMessage.addSource(insertResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("La empresa ha sido insertada satisfactoriamente"));
            }
        });
        successMessage.addSource(updateResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("La empresa ha sido actualizada satisfactoriamente"));
            }
        });
        successMessage.addSource(deleteResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("La empresa ha sido eliminada satisfactoriamente"));
            }
        });
    }

    private void setupErrorMessage() {
        errorMessage.addSource(insertResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al inserta la empresa"));
            }
        });
        errorMessage.addSource(updateResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al actualizar la empresa"));
            }
        });
        errorMessage.addSource(deleteResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al eliminar la empresa"));
            }
        });
    }

    public void insertCompany(Company company) {
        insertTrigger.setValue(company);
    }

    public void updateCompany(Company company) {
        updateTrigger.setValue(company);
    }

    public void deleteCompany(Company company) {
        deleteTrigger.setValue(company);
    }

    public LiveData<Event<String>> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<Event<String>> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Company> queryCompany(long id) {
        return repository.queryCompany(id);
    }
}
