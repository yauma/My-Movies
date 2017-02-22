package com.example.jaimequeraltgarrigos.mymovies.app.interactor;

import com.example.jaimequeraltgarrigos.mymovies.app.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.io.api.MoviesServices;
import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchServerCallback;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
public class MovieSearchInteractor implements MoviesSearch {
    private final MoviesServices service;

    public MovieSearchInteractor(MoviesServices service) {
        this.service = service;
    }

    @Override
    public void fetchLatestMovies(MoviesSearchServerCallback callback) {
        service.getDiscoverMovies(MyConstant.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onMoviesFound
                        , throwable -> {
                            callback.onFailedSearch();
                        });
    }

    @Override
    public void fetchRatestMovies(MoviesSearchServerCallback callback) {
        service.getRatestMovies(MyConstant.API_KEY,MyConstant.MOST_POPULAR)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(callback::onMoviesFound
                       , throwable -> {
                           callback.onFailedSearch();
                       });

    }

    @Override
    public void fetchFavoritesMovies(MoviesSearchServerCallback callback) {

    }
}
