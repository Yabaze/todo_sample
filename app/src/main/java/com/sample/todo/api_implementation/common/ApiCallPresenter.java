package com.sample.todo.api_implementation.common;

import android.content.Context;

import com.sample.todo.api_implementation.ApiAction;
import com.sample.todo.responsePojo.TeacherDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallPresenter {

    ApiAction.GET_Teacher_CLASS_DETAILS get_TeacherCLASS_details;
    ApiContractor apiContractor = ReterofitClient.getInstance().create(ApiContractor.class);
    Context context;

    public ApiCallPresenter(ApiAction.GET_Teacher_CLASS_DETAILS get_TeacherCLASS_details, Context context) {
        this.get_TeacherCLASS_details = get_TeacherCLASS_details;
        this.context = context;
    }

    public void callDetailsAPI() {

        Call<TeacherDetail> call = apiContractor.getAllTeacherDetail();
        call.enqueue(new Callback<TeacherDetail>() {
            @Override
            public void onResponse(Call<TeacherDetail> call, Response<TeacherDetail> response) {
                get_TeacherCLASS_details.onSuccessTeacherClassDetailDetails(response.body());
            }

            @Override
            public void onFailure(Call<TeacherDetail> call, Throwable t) {
                //progressDoalog.dismiss();
                //Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                get_TeacherCLASS_details.onFailureTeacherClassDetailDetails();
            }
        });

    }
}
