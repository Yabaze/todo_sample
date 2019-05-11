package com.sample.todo.api_implementation.common;

import com.sample.todo.database.dao.Semester;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiContractor {

    @GET("/photos")
    Call<List<Semester>> getAllPhotos();

}
