package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(foreignKeys = @ForeignKey(entity = Student.class,
        parentColumns = "id",
        childColumns = "studentId",
        onDelete = RESTRICT,
        onUpdate = CASCADE
))
public class Visit {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long id;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "startTime")
    String startTime;
    @ColumnInfo(name = "endTime")
    String endTime;
    @ColumnInfo(name = "observations")
    String observations;
    @ColumnInfo(name = "studentId")
    long studentId;

    public Visit(long id, String date, String startTime, String endTime, String observations, long studentId) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.observations = observations;
        this.studentId = studentId;
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
}
