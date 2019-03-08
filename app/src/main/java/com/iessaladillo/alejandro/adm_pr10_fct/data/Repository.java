package com.iessaladillo.alejandro.adm_pr10_fct.data;

import com.iessaladillo.alejandro.adm_pr10_fct.base.Resource;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {
    //Student
    LiveData<List<StudentCompany>> queryStudents();
    LiveData<StudentCompany> queryStudent(long studentId);
    LiveData<Resource<Long>> insertStudent(Student student);
    LiveData<Resource<Integer>> updateStudent(Student student);
    LiveData<Resource<Integer>> deleteStudent(Student student);
    //Company
    LiveData<List<Company>> queryCompanies();
    LiveData<Company> queryCompany(long companyId);
    LiveData<Resource<Long>> insertCompany(Company company);
    LiveData<Resource<Integer>> updateCompany(Company company);
    LiveData<Resource<Integer>> deleteCompany(Company company);
    //Visit
    LiveData<List<VisitStudent>> queryVisits();
    LiveData<VisitStudent> queryVisit(long visitId);
    LiveData<Resource<Long>> insertVisit(Visit visit);
    LiveData<Resource<Integer>> updateVisit(Visit visit);
    LiveData<Resource<Integer>> deleteVisit(Visit visit);
    //Next visits
    LiveData<List<VisitStudent>> queryNextVisits();
}
