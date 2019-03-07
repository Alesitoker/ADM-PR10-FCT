package com.iessaladillo.alejandro.adm_pr10_fct.data.local;

import com.iessaladillo.alejandro.adm_pr10_fct.base.BaseDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface StudentDao extends BaseDao<Student> {

    @Query("SELECT s.*, c.name FROM student s JOIN company c ON s.companyId = c.id")
    LiveData<List<StudentCompany>> queryStudents();

    @Query("SELECT s.*, c.name FROM student s JOIN company c ON s.companyId = c.id WHERE s.id = :studentId")
    LiveData<StudentCompany> queryStudent(long studentId);
}
