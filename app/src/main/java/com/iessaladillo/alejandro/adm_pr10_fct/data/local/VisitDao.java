package com.iessaladillo.alejandro.adm_pr10_fct.data.local;

import com.iessaladillo.alejandro.adm_pr10_fct.base.BaseDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface VisitDao extends BaseDao<VisitDao> {

    @Query("SELECT v.*, s.name FROM visit v JOIN student s ON v.studentId = s.id")
    LiveData<List<VisitStudent>> queryVisits();

    @Query("SELECT v.*, s.name FROM visit v JOIN student s ON v.studentId = s.id WHERE v.id = :visitId")
    LiveData<VisitStudent> queryVisit(long visitId);
}
