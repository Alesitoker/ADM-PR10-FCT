package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

import androidx.room.ColumnInfo;

public class StudentCompany {
    long id;
    private String name;
    private int phone;
    private String email;
    private String grade;
    private long companyId;
    private String companyName;
    private String tutorName;
    private int tutorPhone;
    private String schedule;

    public StudentCompany(long id, String name, int phone, String email, String grade, long companyId, String companyName, String tutorName, int tutorPhone, String schedule) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
        this.companyId = companyId;
        this.companyName = companyName;
        this.tutorName = tutorName;
        this.tutorPhone = tutorPhone;
        this.schedule = schedule;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGrade() {
        return grade;
    }

    public long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public int getTutorPhone() {
        return tutorPhone;
    }

    public String getSchedule() {
        return schedule;
    }
}
