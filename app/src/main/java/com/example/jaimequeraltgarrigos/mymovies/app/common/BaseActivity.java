package com.example.jaimequeraltgarrigos.mymovies.app.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jaimequeraltgarrigos.mymovies.R;
import com.example.jaimequeraltgarrigos.mymovies.app.AppComponent;
import com.example.jaimequeraltgarrigos.mymovies.app.MyApplication;

/**
 * Created by jaimequeraltgarrigos on 3/12/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private int layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        injectDependencies();
        setViews();
    }

    protected abstract void setViews();

    private void injectDependencies() {
        setComponent(MyApplication.getApp(this).getComponent());
    }

    protected abstract void setComponent(AppComponent component);

    public abstract int getLayout();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
