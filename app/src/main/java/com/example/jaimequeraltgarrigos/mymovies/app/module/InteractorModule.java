package com.example.jaimequeraltgarrigos.mymovies.app.module;
import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MovieSearchInteractor;
import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MoviesSearch;
import com.example.jaimequeraltgarrigos.mymovies.app.io.api.MoviesServices;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaimequeraltgarrigos on 6/2/16.
 */
@Module
public class InteractorModule {

    @Provides
    public MoviesSearch provideMatchSearchInteractor(MoviesServices services) {
        return new MovieSearchInteractor(services);
    }

}
