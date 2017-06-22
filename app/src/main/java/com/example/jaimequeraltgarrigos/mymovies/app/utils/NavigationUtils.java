package com.example.jaimequeraltgarrigos.mymovies.app.utils;

import android.content.Context;
import android.content.Intent;

import com.example.jaimequeraltgarrigos.mymovies.app.domain.Movie;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.activity.DetailsMovieActivity;
import com.google.gson.Gson;

/**
 * Created by jaimequeraltgarrigos on 22/6/17.
 */

public class NavigationUtils {

    public static void navigateToDetails(Context context, Movie movie) {
        Intent i = new Intent(context, DetailsMovieActivity.class);
        context.startActivity(i);
    }
}
