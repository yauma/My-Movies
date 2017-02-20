package com.example.jaimequeraltgarrigos.mymovies.app.common;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jaimequeraltgarrigos on 5/29/16.
 *  *
 * <p>
 * Base model for every presenter tha will be connected to a fragment or activity
 * </p>
 *
 * <p>
 *  The presenter will be the bridge between Activity or Fragment and an interactor.
 *  Basically a presenter will communicate the results of background tasks (like a server request
 *  or query to database) that are needed to affect the UI
 * </p>
 */
public abstract class BasePresenter {

    /**
     * This method will be executed on
     * {@link AppCompatActivity#onStart()} in case presenter is attached to activity <br>
     * {@link Fragment#onStart()}  in case presenter is attached to fragment
     * */
    public abstract void onStart();

    /**
     * This method will be executed on
     * {@link AppCompatActivity#onStop()} in case presenter is attached to activity <br>
     * {@link Fragment#onStop()}  in case presenter is attached to fragment
     * */
    public abstract void onStop();


}
