package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.*;

@Entity(foreignKeys = @ForeignKey(entity = Company.class,
        parentColumns = "id",
        childColumns = "companyId",
        onDelete = RESTRICT,
        onUpdate = RESTRICT
))
public class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "phone")
    private int phone;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "grade")
    private String grade;
    @ColumnInfo(name = "companyId")
    private long companyId;
    @ColumnInfo(name = "tutorName")
    private String tutorName;
    @ColumnInfo(name = "tutorPhone")
    private int tutorPhone;
    @ColumnInfo(name = "schedule")
    private String schedule;

    public Student(long id, String name, int phone, String email, String grade, long companyId, String tutorName, int tutorPhone, String schedule) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
        this.companyId = companyId;
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
