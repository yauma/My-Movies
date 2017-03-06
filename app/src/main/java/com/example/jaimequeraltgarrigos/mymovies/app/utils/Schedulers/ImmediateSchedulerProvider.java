package com.example.jaimequeraltgarrigos.mymovies.app.utils.Schedulers;

import android.support.annotation.NonNull;
import rx.Scheduler;
import rx.schedulers.Schedulers;


/**
 * Created by jaime.queralt on 06/03/2017.
 */

public class ImmediateSchedulerProvider implements BaseSchedulerProvider {
    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.immediate();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.immediate();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return Schedulers.immediate();
    }

    @NonNull
    @Override
    public Scheduler immediate() {
        return Schedulers.immediate();
    }
}
