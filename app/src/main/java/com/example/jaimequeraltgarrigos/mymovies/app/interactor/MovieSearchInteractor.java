package com.example.jaimequeraltgarrigos.mymovies.app.interactor;

import com.example.jaimequeraltgarrigos.mymovies.app.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.io.api.MoviesServices;
import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchServerCallback;

import java.io.IOException;
import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
public class MovieSearchInteractor implements MoviesSearch {
    private final MoviesServices service;
    private Subscription subscriptionLatestMovies;
    private Subscription subscriptionRatestMovies;


    public MovieSearchInteractor(MoviesServices service) {
        this.service = service;
    }

    @Override
    public void fetchLatestMovies(MoviesSearchServerCallback callback) {
        subscriptionLatestMovies = service.getDiscoverMovies(MyConstant.API_KEY)
                                          .observeOn(AndroidSchedulers.mainThread())
                                          .subscribe(callback::onMoviesFound
                                                  , throwable -> {
                                                      callback.onFailedSearch();
                                                  });

    }

    @Override
    public void fetchRatestMovies(MoviesSearchServerCallback callback) {
        subscriptionRatestMovies = service.getRatestMovies(MyConstant.API_KEY, MyConstant.MOST_POPULAR)
                                          .observeOn(AndroidSchedulers.mainThread())
                                          .subscribe(callback::onMoviesFound
                                                  , throwable -> {
                                                      callback.onFailedSearch();
                                                  });

    }

    @Override
    public void fetchFavoritesMovies(MoviesSearchServerCallback callback) {

    }

    @Override
    public Observable<MoviesResponse> fetchLatestMovies(String searchTerm) {
        return Observable.defer(() -> service.getDiscoverMovies(MyConstant.API_KEY))
                .retryWhen(observable -> observable.concatMap(o -> {
                    if (o instanceof IOException){
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }

    @Override
    public Observable<MoviesResponse> fetchRatestMovies(String searchTerm) {
        return Observable.defer(() -> service.getRatestMovies(MyConstant.API_KEY, MyConstant.MOST_POPULAR))
                         .retryWhen(observable -> observable.concatMap(o -> {
                             if (o instanceof IOException){
                                 return Observable.just(null);
                             }
                             return Observable.error(o);
                         }));
    }
}
