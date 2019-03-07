package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form;

import com.iessaladillo.alejandro.adm_pr10_fct.base.Event;
import com.iessaladillo.alejandro.adm_pr10_fct.base.Resource;
import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class FormStudentFragmentViewModel extends ViewModel {

    private final MutableLiveData<Student> insertTrigger = new MutableLiveData<>();
    private final MutableLiveData<Student> updateTrigger = new MutableLiveData<>();
    private final MutableLiveData<Student> deleteTrigger = new MutableLiveData<>();
    private final MediatorLiveData<Event<String>> successMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> errorMessage = new MediatorLiveData<>();
    private final LiveData<Resource<Long>> insertResult;
    private final LiveData<Resource<Integer>> updateResult;
    private final LiveData<Resource<Integer>> deleteResult;
    Repository repository;

    public FormStudentFragmentViewModel(Repository repository) {
        this.repository = repository;

        insertResult = Transformations.switchMap(insertTrigger, repository::insertStudent);
        updateResult = Transformations.switchMap(updateTrigger, repository::updateStudent);
        deleteResult = Transformations.switchMap(deleteTrigger, repository::updateStudent);

        setupSuccesMessage();
        setupErrorMessage();
    }

    private void setupSuccesMessage() {
        successMessage.addSource(insertResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("El alumno ha sido insertada satisfactoriamente"));
            }
        });
        successMessage.addSource(updateResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("El alumno ha sido actualizada satisfactoriamente"));
            }
        });
        successMessage.addSource(deleteResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("El alumno ha sido eliminada satisfactoriamente"));
            }
        });
    }

    private void setupErrorMessage() {
        errorMessage.addSource(insertResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al inserta el alumno"));
            }
        });
        errorMessage.addSource(updateResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al actualizar el alumno"));
            }
        });
        errorMessage.addSource(deleteResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error al eliminar el alumno"));
            }
        });
    }

    public void insertStudent(Student student) {
        insertTrigger.setValue(student);
    }

    public void updateStudent(Student student) {
        updateTrigger.setValue(student);
    }

    public void deleteStudent(Student student) {
        deleteTrigger.setValue(student);
    }

    public LiveData<Event<String>> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<Event<String>> getErrorMessage() {
        return errorMessage;
    }
}
