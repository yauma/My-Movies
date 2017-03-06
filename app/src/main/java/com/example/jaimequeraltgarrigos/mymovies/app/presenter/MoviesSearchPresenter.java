package com.example.jaimequeraltgarrigos.mymovies.app.presenter;

import com.example.jaimequeraltgarrigos.mymovies.app.utils.Schedulers.BaseSchedulerProvider;
import com.example.jaimequeraltgarrigos.mymovies.app.utils.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MoviesSearch;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.viewmodel.MoviesSearchView;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Pedro Antonio Hernández on 13/06/2015.
 */
public class MoviesSearchPresenter implements PresenterMovies {

    private final BaseSchedulerProvider schedulerProvider;
    MoviesSearchView view;
    MoviesSearch searchInteractor;
    private ArrayList<Object> mDataItems;
    private Subscription subscriptionMovies;
    CompositeSubscription compositeSubscription = new CompositeSubscription();

    public MoviesSearchPresenter(MoviesSearchView view, MoviesSearch interactor, BaseSchedulerProvider schedulerProvider) {
        this.view = view;
        searchInteractor = interactor;
        this.schedulerProvider = schedulerProvider;

    }

    @Override
    public void searchMovies(String query) {

        if (query.equals(MyConstant.DISCOVER)) {

            searchInteractor.fetchLatestMovies(query).subscribeOn(schedulerProvider.io())
                                                 .observeOn(schedulerProvider.ui()).subscribe(new Subscriber<MoviesResponse>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(MoviesResponse moviesResponse) {
                    view.displayFoundMovies(moviesResponse.getMovies());
                }
            });
        } else if (query.equals(MyConstant.LIVE)) {
/*            subscriptionMovies = searchInteractor.fetchRatestMovies(query).subscribeOn(Schedulers.io())
                                                 .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);*/
        } else {

        }
    }

    @Override
    public void unsubscribe() {
        if (subscriptionMovies != null) {
            subscriptionMovies.unsubscribe();
        }
    }
}
