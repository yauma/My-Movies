package com.example.jaimequeraltgarrigos.mymovies.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jaimequeraltgarrigos.mymovies.R;
import com.example.jaimequeraltgarrigos.mymovies.app.AppComponent;
import com.example.jaimequeraltgarrigos.mymovies.app.common.BaseActivity;

public class DetailsMovieActivity extends BaseActivity {

    @Override
    protected void setViews() {
        setContentView(R.layout.activity_details_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void setComponent(AppComponent component) {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_details_movie;
    }
}
