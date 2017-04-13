package com.example.jaimequeraltgarrigos.mymovies.app.io.api;

import com.example.jaimequeraltgarrigos.mymovies.app.utils.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.MoviesResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
public interface MoviesServices {
    @GET(MyConstant.URL_DISCOVER_MOVIE)
    Observable<MoviesResponse> getDiscoverMovies(@Query("api_key") String apiKey,@Query("page") int page);
    @GET(MyConstant.URL_DISCOVER_MOVIE)
    Observable<MoviesResponse> getRatestMovies (@Query("api_key") String apiKey,@Query("page") int page, @Query("sort_by") String sortBy);

}
