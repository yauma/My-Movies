package com.example.jaimequeraltgarrigos.mymovies.app;

import android.content.Context;

import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MovieSearchInteractor;
import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MoviesSearch;
import com.example.jaimequeraltgarrigos.mymovies.app.module.InteractorModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jaimequeraltgarrigos on 6/2/16.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                InteractorModule.class
        })
public interface AppComponent {
    Context getContext();
    MoviesSearch getMovieSearchInteractor();
}
