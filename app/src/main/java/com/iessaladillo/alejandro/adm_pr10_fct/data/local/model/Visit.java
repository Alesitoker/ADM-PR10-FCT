package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

public class Visit {
    long id;
    String day;
    String startTime;
    String endTime;
    String observations;
    long studentId;
    String studentName;

    public Visit(long id, String day, String startTime, String endTime, String observations, long studentId, String studentName) {
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

    public String getStudentName() {
        return studentName;
    }
}
