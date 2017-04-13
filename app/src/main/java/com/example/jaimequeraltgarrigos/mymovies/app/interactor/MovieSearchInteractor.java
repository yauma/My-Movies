package com.example.jaimequeraltgarrigos.mymovies.app.interactor;

import com.example.jaimequeraltgarrigos.mymovies.app.utils.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.io.api.MoviesServices;

import rx.Observable;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
public class MovieSearchInteractor implements MoviesSearch {
    private final MoviesServices service;


    public MovieSearchInteractor(MoviesServices service) {
        this.service = service;
    }

    @Override
    public Observable<MoviesResponse> fetchLatestMovies(String searchTerm, int page) {
        return Observable.defer(() -> service.getDiscoverMovies(MyConstant.API_KEY, page));
    }

    @Override
    public Observable<MoviesResponse> fetchRatestMovies(String searchTerm, int page) {
        return Observable.defer(() -> service.getRatestMovies(MyConstant.API_KEY, page, MyConstant.MOST_POPULAR));
    }
}
