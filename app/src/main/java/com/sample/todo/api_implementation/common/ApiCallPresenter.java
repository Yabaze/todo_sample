package com.sample.todo.api_implementation.common;

import android.content.Context;
import android.util.Log;

import com.sample.todo.api_implementation.ApiAction;
import com.sample.todo.database.dao.Semester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallPresenter {

    ApiAction.GET_DETAILS get_details;
    ApiContractor apiContractor = ReterofitClient.getInstance().create(ApiContractor.class);
    Context context;

    public ApiCallPresenter(ApiAction.GET_DETAILS get_details, Context context) {
        this.get_details = get_details;
        this.context = context;
    }

    public void callDetailsAPI() {

        Call<List<Semester>> call = apiContractor.getAllPhotos();
        call.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                Log.e("MIRAKLE",response.toString());
                Semester semester = new Semester("yabaze",new Date(System.currentTimeMillis()),"4:30 pm");
                get_details.onSuccessDetails(new ArrayList<>(Collections.singleton(semester)));
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                //progressDoalog.dismiss();
                //Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                get_details.onFailureDetails();
            }
        });

    }
}
