package com.example.jaimequeraltgarrigos.mymovies.app.interactor;

import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchServerCallback;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
public interface MoviesSearch {

    void fetchLatestMovies(MoviesSearchServerCallback callback);
    void fetchRatestMovies(MoviesSearchServerCallback callback);
    void fetchFavoritesMovies(MoviesSearchServerCallback callback);


}
