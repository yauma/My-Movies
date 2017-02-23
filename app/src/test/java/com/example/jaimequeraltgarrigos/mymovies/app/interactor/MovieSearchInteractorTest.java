package com.example.jaimequeraltgarrigos.mymovies.app.interactor;

import com.example.jaimequeraltgarrigos.mymovies.app.domain.Movie;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;
import com.example.jaimequeraltgarrigos.mymovies.app.io.api.MoviesServices;
import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchServerCallback;

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

    private MovieSearchInteractor movieSearchInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movieSearchInteractor = new MovieSearchInteractor(service);
    }

    @Test
    public void fetchLatestMovies_200OkResponse_InvokesCorrectApiCalls() {
        //Given
        when(service.getDiscoverMovies(anyString())).thenReturn(Observable.just(mockMoviesList()));

        //When
        TestSubscriber<MoviesResponse> subscriber = new TestSubscriber<>();
        service.getDiscoverMovies(anyString()).subscribe(subscriber);

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
    public void fetchLatestMovies_Error_SearchTerminatedWithError() {

        //Given
        when(service.getDiscoverMovies(anyString())).thenReturn(getIOException());

        //When
        TestSubscriber<MoviesResponse> subscriber = new TestSubscriber<>();
        service.getDiscoverMovies(anyString()).subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertError(IOException.class);

        List<Throwable> throwables = subscriber.getOnErrorEvents();
        Assert.assertEquals(throwables.size(),1);

        verify(service).getDiscoverMovies(anyString());


        /*//Given
        when(githubUserRestService.searchGithubUsers(anyString())).thenReturn(get403ForbiddenError());

        //When
        TestSubscriber<List<User>> subscriber = new TestSubscriber<>();
        userRepository.searchUsers(USER_LOGIN_RIGGAROO).subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertError(HttpException.class);

        verify(githubUserRestService).searchGithubUsers(USER_LOGIN_RIGGAROO);

        verify(githubUserRestService, never()).getUser(USER_LOGIN_RIGGAROO);
        verify(githubUserRestService, never()).getUser(USER_LOGIN_2_REBECCA);*/
    }

    private Observable<MoviesResponse> getIOException() {
        return Observable.error(new IOException());
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