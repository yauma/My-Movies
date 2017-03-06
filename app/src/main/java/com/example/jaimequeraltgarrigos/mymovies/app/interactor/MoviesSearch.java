package com.example.jaimequeraltgarrigos.mymovies.app.interactor;

import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;

import rx.Observable;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
public interface MoviesSearch {

    Observable<MoviesResponse> fetchLatestMovies(final String searchTerm);
    Observable<MoviesResponse> fetchRatestMovies(final String searchTerm);



}
