package com.example.jaimequeraltgarrigos.mymovies.app.presenter;
import com.example.jaimequeraltgarrigos.mymovies.app.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.Movie;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MoviesSearch;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.viewmodel.MoviesSearchView;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pedro Antonio Hernández on 13/06/2015.
 */
public class MoviesSearchPresenter implements MoviesSearchServerCallback {

    MoviesSearchView view;
    MoviesSearch searchInteractor;
    private ArrayList<Object> mDataItems;
    private Subscription subscriptionMovies;

    public MoviesSearchPresenter(MoviesSearchView view, MoviesSearch interactor) {
        this.view = view;
        searchInteractor = interactor;
    }

    public void searchMovies(String query) {
        Subscriber subscriber = new Subscriber <MoviesResponse>() {
            @Override
            public void onCompleted() {
                view.showProgressBar(false);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MoviesResponse moviesResponse) {
                view.displayFoundMovies(moviesResponse.getMovies());
            }

        };
        if (query.equals(MyConstant.DISCOVER)) {
            subscriptionMovies = searchInteractor.fetchLatestMovies(query).subscribeOn(Schedulers.io())
                                                 .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        } else if (query.equals(MyConstant.LIVE)){
            subscriptionMovies = searchInteractor.fetchRatestMovies(query).subscribeOn(Schedulers.io())
                                                 .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        }else {

        }
    }

    @Override
    public void unsubscribe() {
        if (subscriptionMovies != null){
            subscriptionMovies.unsubscribe();
        }
    }
}
