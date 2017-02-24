package com.example.jaimequeraltgarrigos.mymovies.app.interactor;

import android.accounts.Account;

import com.example.jaimequeraltgarrigos.mymovies.app.domain.Movie;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.io.api.MoviesServices;
import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchServerCallback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.HttpException;
import retrofit2.Response;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jaime.queralt on 23/02/2017.
 */
public class MovieSearchInteractorTest {
    @Mock
    MoviesServices service;
    @Mock
    MoviesSearchServerCallback callback;

    private MoviesSearch moviesSearch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        moviesSearch = new MovieSearchInteractor(service);
    }

    @Test
    public void fetchLatestMovies_200OkResponse_InvokesCorrectApiCalls() {
        //Given
        when(service.getDiscoverMovies(anyString())).thenReturn(Observable.just(mockMoviesList()));

        //When
        TestSubscriber<MoviesResponse> subscriber = new TestSubscriber<>();
        moviesSearch.fetchLatestMovies(anyString()).subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        List<Throwable> throwables = subscriber.getOnErrorEvents();
        Assert.assertEquals(throwables.size(),0);

        List<MoviesResponse> onNextEvents = subscriber.getOnNextEvents();
        MoviesResponse movieResponse = onNextEvents.get(0);
        Assert.assertEquals("Movie 1", movieResponse.getMovies().get(0).getTitle());
        Assert.assertEquals("Movie 2", movieResponse.getMovies().get(1).getTitle());
    }

    @Test
    public void fetchLatestMovies_403Error_SearchTerminatedWithError() {

        //Given
        when(service.getDiscoverMovies(anyString())).thenReturn(get403Error());

        //When
        TestSubscriber<MoviesResponse> subscriber = new TestSubscriber<>();
        moviesSearch.fetchLatestMovies(anyString()).subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertError(HttpException.class);

        List<Throwable> throwables = subscriber.getOnErrorEvents();
        Assert.assertEquals(throwables.size(),1);

        verify(service).getDiscoverMovies(anyString());
    }

    @Test
    public void fetchRatestMovies_200OkResponse_InvokesCorrectApiCalls() {
        //Given
        when(service.getRatestMovies(anyString(),anyString())).thenReturn(Observable.just(mockMoviesList()));

        //When
        TestSubscriber<MoviesResponse> subscriber = new TestSubscriber<>();
        moviesSearch.fetchRatestMovies("").subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        List<Throwable> throwables = subscriber.getOnErrorEvents();
        Assert.assertEquals(throwables.size(),0);

        List<MoviesResponse> onNextEvents = subscriber.getOnNextEvents();
        MoviesResponse movieResponse = onNextEvents.get(0);
        Assert.assertEquals("Movie 1", movieResponse.getMovies().get(0).getTitle());
        Assert.assertEquals("Movie 2", movieResponse.getMovies().get(1).getTitle());
    }

    @Test
    public void fetchRatestMovies_403Error_SearchTerminatedWithError() {

        //Given
        when(service.getRatestMovies(anyString(),anyString())).thenReturn(get403Error());

        //When
        TestSubscriber<MoviesResponse> subscriber = new TestSubscriber<>();
        moviesSearch.fetchRatestMovies("").subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertError(Exception.class);

        List<Throwable> throwables = subscriber.getOnErrorEvents();
        Assert.assertEquals(throwables.size(),1);

        verify(service).getRatestMovies(anyString(),anyString());
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

    private MoviesResponse mockMoviesList() {
        MoviesResponse moviesResponse = new MoviesResponse();
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setTitle("Movie 1");
        Movie movie2 = new Movie();
        movie2.setTitle("Movie 2");
        movies.add(movie1);
        movies.add(movie2);
        moviesResponse.setMovies(movies);
        return moviesResponse;
    }

}