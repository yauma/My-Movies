package com.example.jaimequeraltgarrigos.mymovies.app.presenter;

import android.accounts.Account;

import com.example.jaimequeraltgarrigos.mymovies.app.utils.Schedulers.ImmediateSchedulerProvider;
import com.example.jaimequeraltgarrigos.mymovies.app.utils.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.Movie;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.interactor.MoviesSearch;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.viewmodel.MoviesSearchView;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.HttpException;
import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jaime.queralt on 06/03/2017.
 */
public class MoviesSearchPresenterTest {

    @Mock
    MoviesSearchView view;
    @Mock
    MoviesSearch searchInteractor;

    ImmediateSchedulerProvider baseSchedulerProvider;
    MoviesSearchPresenter moviesSearchPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        baseSchedulerProvider = new ImmediateSchedulerProvider();
        moviesSearchPresenter = new MoviesSearchPresenter(view, searchInteractor, baseSchedulerProvider);
    }

    @Test
    public void search_ValidSearchTerm_ReturnsResults() {
        MoviesResponse moviesResponse = getDummyMoviesResponse();
        when(searchInteractor.fetchLatestMovies(anyString())).thenReturn(Observable.just(moviesResponse));
        moviesSearchPresenter.searchMovies(MyConstant.DISCOVER);
        verify(view).displayFoundMovies(moviesResponse.getMovies());
        verify(view).showProgressBar(false);
        verify(view, never()).displayNetworkError();
        verify(view, never()).displayFailedSearch();
        verify(view, never()).displayServerError();
    }

    @Test
    public void search_InValidSearchTerm_ReturnNothing() {
        MoviesResponse moviesResponse = getDummyMoviesResponse();
        when(searchInteractor.fetchLatestMovies(anyString())).thenReturn(Observable.just(moviesResponse));
        moviesSearchPresenter.searchMovies("");
        verify(view, never()).displayFoundMovies(moviesResponse.getMovies());
        verify(view, never()).showProgressBar(false);
        verify(view, never()).displayNetworkError();
        verify(view).displayFailedSearch();
        verify(view, never()).displayServerError();
    }

    @Test
    public void search_IOException_ReturnNothing() {
        MoviesResponse moviesResponse = getDummyMoviesResponse();
        when(searchInteractor.fetchLatestMovies(anyString())).thenReturn(getIOException());
        moviesSearchPresenter.searchMovies(MyConstant.DISCOVER);
        verify(view, never()).displayFoundMovies(moviesResponse.getMovies());
        verify(view, never()).showProgressBar(false);
        verify(view).displayNetworkError();
        verify(view, never()).displayFailedSearch();
        verify(view, never()).displayServerError();
    }

    @Test
    public void search_HttpException_ReturnNothing() {
        MoviesResponse moviesResponse = getDummyMoviesResponse();
        when(searchInteractor.fetchLatestMovies(anyString())).thenReturn(get403Error());
        moviesSearchPresenter.searchMovies(MyConstant.DISCOVER);
        verify(view, never()).displayFoundMovies(moviesResponse.getMovies());
        verify(view, never()).showProgressBar(false);
        verify(view, never()).displayNetworkError();
        verify(view, never()).displayFailedSearch();
        verify(view).displayServerError();
    }

    MoviesResponse getDummyMoviesResponse() {
        MoviesResponse moviesResponse = new MoviesResponse();
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movie1.setTitle("Movie 1");
        movie2.setTitle("Movie 2");
        movies.add(movie1);
        movies.add(movie2);
        moviesResponse.setMovies(movies);
        return moviesResponse;
    }

    private Observable<MoviesResponse> get403Error() {
        retrofit.Response<Account> aResponse = retrofit.Response.error(
                403,
                ResponseBody.create(
                        MediaType.parse("application/json"),
                        "{\"key\":[\"somestuff\"]}"
                )
        );
        return Observable.error(new HttpException(aResponse));
    }

    private Observable<MoviesResponse> getIOException() {
        return Observable.error(new IOException());
    }
}