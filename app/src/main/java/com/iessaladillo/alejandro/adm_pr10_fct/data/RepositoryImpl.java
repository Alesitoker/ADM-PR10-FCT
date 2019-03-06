package com.iessaladillo.alejandro.adm_pr10_fct.data;

import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryImpl implements Repository {

    @Override
    public LiveData<List<Student>> queryStudents() {
        return null;
    }

    @Override
    public LiveData<Student> queryStudent(long studentId) {
        return null;
    }

    @Override
    public void insertStudent(Student student) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(Student student) {

    }

    @Override
    public LiveData<List<Company>> queryCompanies() {
        return null;
    }

    @Override
    public LiveData<Company> queryCompany() {
        return null;
    }

    @Override
    public void insertCompany() {

    }

    @Override
    public void updateCompany() {

    }

    @Override
    public void deleteCompany() {

    }

    @Override
    public LiveData<List<Visit>> queryVisits() {
        return null;
    }

    @Override
    public LiveData<Visit> queryVisit() {
        return null;
    }

    @Override
    public void insertVisit() {

    }

    @Override
    public void updateVisit() {

    }

    @Override
    public void deleteVisit() {

    }
}
