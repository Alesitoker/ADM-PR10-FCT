package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

public class Visit {
    long id;
    String date;
    String startTime;
    String endTime;
    String observations;
    long studentId;
    String studentName;

    public Visit(long id, String date, String startTime, String endTime, String observations, long studentId, String studentName) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.observations = observations;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
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

    public String getStudentName() {
        return studentName;
    }
}
