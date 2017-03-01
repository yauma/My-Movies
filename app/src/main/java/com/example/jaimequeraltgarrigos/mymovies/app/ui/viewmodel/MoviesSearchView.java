package com.example.jaimequeraltgarrigos.mymovies.app.ui.viewmodel;

import com.example.jaimequeraltgarrigos.mymovies.app.domain.Movie;

import java.util.List;

/**
 * Created by jaimequeraltgarrigos on 5/29/16.
 */
public interface MoviesSearchView {

    void showProgressBar(boolean visibility);

    void displayFoundMovies(List<Movie> movies);

    void displayFailedSearch();

    void displayNetworkError();

    void displayServerError();

}
