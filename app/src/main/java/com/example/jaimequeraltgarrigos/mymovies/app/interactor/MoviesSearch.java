package com.example.jaimequeraltgarrigos.mymovies.app.interactor;

import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchServerCallback;

import rx.Observable;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
public interface MoviesSearch {

    void fetchLatestMovies(MoviesSearchServerCallback callback);
    void fetchRatestMovies(MoviesSearchServerCallback callback);
    void fetchFavoritesMovies(MoviesSearchServerCallback callback);
    Observable<MoviesResponse> fetchLatestMovies(final String searchTerm);
    Observable<MoviesResponse> fetchRatestMovies(final String searchTerm);



}
