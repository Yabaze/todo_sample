package com.sample.todo.api_implementation;

import com.sample.todo.database.dao.Semester;

import java.util.List;

public class ApiAction {

    public interface GET_DETAILS {

        void onSuccessDetails(List<Semester> semesterList);

        void onFailureDetails();
    }
}
