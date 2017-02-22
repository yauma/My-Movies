package com.example.jaimequeraltgarrigos.mymovies.app.presenter;
import com.example.jaimequeraltgarrigos.mymovies.app.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MoviesSearch;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.viewmodel.MoviesSearchView;

import java.util.ArrayList;

/**
 * Created by Pedro Antonio Hernández on 13/06/2015.
 */
public class MoviesSearchPresenter implements MoviesSearchServerCallback {

    MoviesSearchView view;
    MoviesSearch searchInteractor;
    private ArrayList<Object> mDataItems;

    public MoviesSearchPresenter(MoviesSearchView view, MoviesSearch interactor) {
        this.view = view;
        searchInteractor = interactor;
    }

    public void searchMovies(String query) {
        if (query.equals(MyConstant.DISCOVER)) {
            searchInteractor.fetchLatestMovies(this);
        } else if (query.equals(MyConstant.LIVE)){
            searchInteractor.fetchRatestMovies(this);
        }else {
            searchInteractor.fetchFavoritesMovies(this);
        }

    }


    @Override
    public void onMoviesFound(MoviesResponse movies) {
        view.displayFoundMovies(movies.getMovies());
    }

    @Override
    public void onFailedSearch() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onServerError() {

    }
}
