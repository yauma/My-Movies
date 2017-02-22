package com.example.jaimequeraltgarrigos.mymovies.app.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.jaimequeraltgarrigos.mymovies.R;
import com.example.jaimequeraltgarrigos.mymovies.app.AppComponent;
import com.example.jaimequeraltgarrigos.mymovies.app.common.BaseFragment;
import com.example.jaimequeraltgarrigos.mymovies.app.component.DaggerMovieSearchComponent;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.Movie;
import com.example.jaimequeraltgarrigos.mymovies.app.module.MovieSearchModule;
import com.example.jaimequeraltgarrigos.mymovies.app.presenter.MoviesSearchPresenter;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.adapter.MoviesAdapter;
import com.example.jaimequeraltgarrigos.mymovies.app.ui.viewmodel.MoviesSearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */

public class MoviesViewerFragment extends BaseFragment implements MoviesSearchView {

    /*    @BindView(R.id.textView2)
        TextView textView;*/
    @BindView(R.id.recyclerview_grid)
    RecyclerView mRecyclerView;

    @Inject
    MoviesSearchPresenter presenter;
    @Inject
    MoviesAdapter adapter;

    private String query;

    public static MoviesViewerFragment newInstance(String query) {
        MoviesViewerFragment moviesViewerFragment = new MoviesViewerFragment();

        Bundle args = new Bundle();
        args.putString("query", query);
        moviesViewerFragment.setArguments(args);

        return moviesViewerFragment;
    }

    @Override
    protected void viewCreated() {
        query = getArguments().getString("query");
        setupList(mRecyclerView, adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.searchMovies(query);
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerMovieSearchComponent.builder().appComponent(component)
                                  .movieSearchModule(new MovieSearchModule(this)).build().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_movies_viewer;
    }

    @Override
    public void displayFoundMovies(List<Movie> movies) {
        adapter.addMovies(movies);
    }

    @Override
    public void displayFailedSearch() {

    }

    @Override
    public void displayNetworkError() {

    }

    @Override
    public void displayServerError() {

    }
}
