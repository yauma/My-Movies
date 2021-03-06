package com.example.jaimequeraltgarrigos.mymovies.app;

import android.content.Context;

import com.example.jaimequeraltgarrigos.mymovies.app.io.api.MoviesServices;
import com.example.jaimequeraltgarrigos.mymovies.app.io.api.RestApiManager;
import com.example.jaimequeraltgarrigos.mymovies.app.utils.Schedulers.BaseSchedulerProvider;
import com.example.jaimequeraltgarrigos.mymovies.app.utils.Schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;


/**
 * Created by jaimequeraltgarrigos on 6/1/16.
 */
@Module
public class AppModule {

    MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    MyApplication providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitInstance() {
        return RestApiManager.getInstance();
    }

    @Provides
    @Singleton
    public MoviesServices provideApiService(Retrofit retrofit) {
        return retrofit.create(MoviesServices.class);
    }

    @Provides
    public BaseSchedulerProvider provideIoSchedulers() {
        return new SchedulerProvider();
    }


}
