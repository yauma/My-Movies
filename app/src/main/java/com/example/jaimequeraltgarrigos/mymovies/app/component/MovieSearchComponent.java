package com.example.jaimequeraltgarrigos.mymovies.app.component;

import com.example.jaimequeraltgarrigos.mymovies.app.ActivityScope;
import com.example.jaimequeraltgarrigos.mymovies.app.AppComponent;
import com.example.jaimequeraltgarrigos.mymovies.app.module.MovieSearchModule;
import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchPresenter;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.adapter.MoviesAdapter;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.fragment.MoviesViewerFragment;

import dagger.Component;
import rx.schedulers.Schedulers;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MovieSearchModule.class
)
public interface MovieSearchComponent {
    void inject(MoviesViewerFragment fragment);

    MoviesSearchPresenter getPresenter();

    MoviesAdapter getAdapter();
}