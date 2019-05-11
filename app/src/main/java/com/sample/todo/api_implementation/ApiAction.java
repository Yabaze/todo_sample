package com.sample.todo.api_implementation;

import com.sample.todo.database.dao.entity.Semester;
import com.sample.todo.responsePojo.TeacherDetail;

import java.util.List;

public class ApiAction {

    public interface GET_Teacher_CLASS_DETAILS {

        void onSuccessTeacherClassDetailDetails(TeacherDetail semesterList);

        void onFailureTeacherClassDetailDetails();
    }
}
