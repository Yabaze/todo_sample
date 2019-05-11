
package com.sample.todo.responsePojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeacherDetail  {

    @SerializedName("TeacherId")
    @Expose
    private Integer teacherId;
    @SerializedName("TeacherName")
    @Expose
    private String teacherName;
    @SerializedName("Classes")
    @Expose
    private List<ClassDetail> classDetails = null;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<ClassDetail> getClassDetails() {
        return classDetails;
    }

    public void setClassDetails(List<ClassDetail> classDetails) {
        this.classDetails = classDetails;
    }

}
