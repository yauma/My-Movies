package com.example.jaimequeraltgarrigos.mymovies.app.presenter;

import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
public interface MoviesSearchServerCallback {

    void onMoviesFound(MoviesResponse movies);

    void onFailedSearch();

    void onNetworkError();

    void onServerError();
}
