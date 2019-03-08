package com.iessaladillo.alejandro.adm_pr10_fct.data.local;

import com.iessaladillo.alejandro.adm_pr10_fct.base.BaseDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface VisitDao extends BaseDao<Visit> {

    @Query("SELECT v.id, v.day, v.startTime, v.endTime, v.observations, v.studentId, s.name AS studentName FROM visit v JOIN student s ON v.studentId = s.id")
    LiveData<List<VisitStudent>> queryVisits();

    @Query("SELECT v.id, v.day, v.startTime, v.endTime, v.observations, v.studentId, s.name AS studentName FROM visit v JOIN student s ON v.studentId = s.id WHERE v.id = :visitId")
    LiveData<VisitStudent> queryVisit(long visitId);

    @Query("SELECT v.id, v.day, v.startTime, v.endTime, v.observations, v.studentId, s.name AS studentName " +
            "FROM student s LEFT JOIN visit v ON s.id = v.studentId GROUP BY s.id ORDER BY s.name, v.day, v.startTime")
    LiveData<List<VisitStudent>> queryNextVisits();
}
