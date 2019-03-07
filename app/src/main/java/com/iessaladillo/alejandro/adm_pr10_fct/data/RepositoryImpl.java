package com.iessaladillo.alejandro.adm_pr10_fct.data;

import com.iessaladillo.alejandro.adm_pr10_fct.base.Resource;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryImpl implements Repository {


    @Override
    public LiveData<List<StudentCompany>> queryStudents() {
        return null;
    }

    @Override
    public LiveData<StudentCompany> queryStudent(long studentId) {
        return null;
    }

    @Override
    public LiveData<Resource<Long>> insertStudent(Student student) {

        return null;
    }

    @Override
    public LiveData<Resource<Integer>> updateStudent(Student student) {

        return null;
    }

    @Override
    public LiveData<Resource<Integer>> deleteStudent(Student student) {

        return null;
    }

    @Override
    public LiveData<List<Company>> queryCompanies() {
        return null;
    }

    @Override
    public LiveData<Company> queryCompany(long companyId) {
        return null;
    }

    @Override
    public LiveData<Resource<Long>> insertCompany(Company company) {

        return null;
    }

    @Override
    public LiveData<Resource<Integer>> updateCompany(Company company) {

        return null;
    }

    @Override
    public LiveData<Resource<Integer>> deleteCompany(Company company) {

        return null;
    }

    @Override
    public LiveData<List<VisitStudent>> queryVisits() {
        return null;
    }

    @Override
    public LiveData<VisitStudent> queryVisit(long visitId) {
        return null;
    }

    @Override
    public LiveData<Resource<Long>> insertVisit(Visit visit) {

        return null;
    }

    @Override
    public LiveData<Resource<Integer>> updateVisit(Visit visit) {

        return null;
    }

    @Override
    public LiveData<Resource<Integer>> deleteVisit(Visit visit) {

        return null;
    }
}
