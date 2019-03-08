package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form;

import com.iessaladillo.alejandro.adm_pr10_fct.base.Event;
import com.iessaladillo.alejandro.adm_pr10_fct.base.Resource;
import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class FormVisitFragmentViewModel extends ViewModel {

    private final MutableLiveData<Visit> insertTrigger = new MutableLiveData<>();
    private final MutableLiveData<Visit> updateTrigger = new MutableLiveData<>();
    private final MutableLiveData<Visit> deleteTrigger = new MutableLiveData<>();
    private final MediatorLiveData<Event<String>> successMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> errorMessage = new MediatorLiveData<>();
    private final LiveData<Resource<Long>> insertResult;
    private final LiveData<Resource<Integer>> updateResult;
    private final LiveData<Resource<Integer>> deleteResult;
    private long editId;
    private long studentId;
    private String whatTime;
    Repository repository;

    public FormVisitFragmentViewModel(Repository repository) {
        this.repository = repository;

        insertResult = Transformations.switchMap(insertTrigger, repository::insertVisit);
        updateResult = Transformations.switchMap(updateTrigger, repository::updateVisit);
        deleteResult = Transformations.switchMap(deleteTrigger, repository::deleteVisit);

        setupSuccesMessage();
        setupErrorMessage();
    }

    private void setupSuccesMessage() {
        successMessage.addSource(insertResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("La visita ha sido insertada satisfactoriamente"));
            }
        });
        successMessage.addSource(updateResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("La visita ha sido actualizada satisfactoriamente"));
            }
        });
        successMessage.addSource(deleteResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("La visita ha sido eliminada satisfactoriamente"));
            }
        });
    }

    private void setupErrorMessage() {
        errorMessage.addSource(insertResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al inserta la visita"));
            }
        });
        errorMessage.addSource(updateResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al actualizar la visita"));
            }
        });
        errorMessage.addSource(deleteResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al eliminar la visita"));
            }
        });
    }

    public void insertVisit(Visit visit) {
        insertTrigger.setValue(visit);
    }

    public void updateVisit(Visit visit) {
        updateTrigger.setValue(visit);
    }

    public void deleteVisit(Visit visit) {
        deleteTrigger.setValue(visit);
    }

    public LiveData<Event<String>> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<Event<String>> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<VisitStudent> queryVisit() {
        return repository.queryVisit(editId);
    }

    public long getEditId() {
        return editId;
    }

    public void setEditId(long editId) {
        this.editId = editId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getWhatTime() {
        return whatTime;
    }

    public void setWhatTime(String whatTime) {
        this.whatTime = whatTime;
    }
}
