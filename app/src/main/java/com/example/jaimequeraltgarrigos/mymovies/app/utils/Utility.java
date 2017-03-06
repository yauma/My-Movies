package com.example.jaimequeraltgarrigos.mymovies.app.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by jaime.queralt on 20/02/2017.
 */

public class Utility {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }
}
