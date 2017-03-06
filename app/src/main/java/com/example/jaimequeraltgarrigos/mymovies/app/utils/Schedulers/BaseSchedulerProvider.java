package com.example.jaimequeraltgarrigos.mymovies.app.utils.Schedulers;

import android.support.annotation.NonNull;

import rx.Scheduler;

/**
 * Created by jaime.queralt on 06/03/2017.
 */

public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();

    @NonNull
    Scheduler immediate();
}
