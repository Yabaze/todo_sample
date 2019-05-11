package com.sample.todo.database.dao.entity;

import com.sample.todo.database.dao.DateConverter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "class_table")
public class ClassTable {

    @PrimaryKey
    @ColumnInfo(name = "class_id")
    private Integer class_id;

    @ColumnInfo(name = "name")
    private String class_name;

    @ColumnInfo(name = "start_time")
    @TypeConverters(DateConverter.class)
    private String start_time;

    @ColumnInfo(name = "end_time")
    @TypeConverters(DateConverter.class)
    private String end_time;

    public ClassTable(Integer class_id, String class_name, String start_time, String end_time) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
