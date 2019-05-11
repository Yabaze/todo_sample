package com.sample.todo.database.dao;


import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "semester")
public class Semester {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String semesterName;

    @ColumnInfo(name = "start_time")
    @TypeConverters(DateConverter.class)
    public Date startTime;

    @ColumnInfo(name = "end_time")
    public String endTime;

    public Semester(String semesterName, Date startTime, String endTime) {

        this.semesterName = semesterName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
