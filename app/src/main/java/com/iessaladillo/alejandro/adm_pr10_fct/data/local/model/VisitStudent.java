package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

import androidx.room.ColumnInfo;

public class VisitStudent {
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "day")
    private String day;
    @ColumnInfo(name = "startTime")
    private String startTime;
    @ColumnInfo(name = "endTime")
    private String endTime;
    @ColumnInfo(name = "observations")
    private String observations;
    @ColumnInfo(name = "studentId")
    private long studentId;
    private String studentName;

    public VisitStudent(long id, String day, String startTime, String endTime, String observations, long studentId, String studentName) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.observations = observations;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getObservations() {
        return observations;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}
