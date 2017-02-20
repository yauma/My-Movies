package com.example.jaimequeraltgarrigos.mymovies.app.module;

import android.content.Context;

import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MovieSearchInteractor;
import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MoviesSearch;
import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchPresenter;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.adapter.MoviesAdapter;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.viewmodel.MoviesSearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */

@Module
public class MovieSearchModule {
    MoviesSearchView view;

    public MovieSearchModule(MoviesSearchView view) {
        this.view = view;
    }

    @Provides
    public MoviesSearchView provideView() {
        return view;
    }

    @Provides
    public MoviesSearchPresenter providePresenter(MoviesSearchView view, MoviesSearch interactor) {
        return new MoviesSearchPresenter(view, interactor);
    }

    @Provides
    public MoviesAdapter provideMoviesAdapter(Context context) {
        return new MoviesAdapter(context);
    }
}
