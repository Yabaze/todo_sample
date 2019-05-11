package com.sample.todo.api_implementation.common;

import com.sample.todo.responsePojo.TeacherDetail;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiContractor {

    @GET("/bins/14zs86")
    Call<TeacherDetail> getAllTeacherDetail();

}
