package com.example.jaimequeraltgarrigos.mymovies.app.io.api;


import com.example.jaimequeraltgarrigos.mymovies.app.MyConstant;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


/**
 * Created by jaimequeraltgarrigos on 5/17/16.
 */
public class RestApiManager {
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(MyConstant.API_BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
