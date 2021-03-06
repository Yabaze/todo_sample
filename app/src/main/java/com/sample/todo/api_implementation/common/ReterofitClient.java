package com.sample.todo.api_implementation.common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReterofitClient {

    private static Retrofit retrofit;
    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String BASE_URL = "https://api.myjson.com/";

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
