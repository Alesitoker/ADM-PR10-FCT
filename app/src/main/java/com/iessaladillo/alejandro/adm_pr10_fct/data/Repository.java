package com.iessaladillo.alejandro.adm_pr10_fct.data;

import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {
    //Student
    LiveData<List<Student>> queryStudents();
    LiveData<Student> queryStudent(long studentId);
    void insertStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);
    //Company
    LiveData<List<Company>> queryCompanies();
    LiveData<Company> queryCompany();
    void insertCompany();
    void updateCompany();
    void deleteCompany();
    //Visit
    LiveData<List<Visit>> queryVisits();
    LiveData<Visit> queryVisit();
    void insertVisit();
    void updateVisit();
    void deleteVisit();
}
