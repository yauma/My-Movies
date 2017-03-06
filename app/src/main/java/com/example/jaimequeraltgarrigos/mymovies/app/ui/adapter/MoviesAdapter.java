package com.example.jaimequeraltgarrigos.mymovies.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jaimequeraltgarrigos.mymovies.R;
import com.example.jaimequeraltgarrigos.mymovies.app.utils.MyConstant;
import com.example.jaimequeraltgarrigos.mymovies.app.domain.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaimequeraltgarrigos on 6/11/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {


    private List<Movie> movies;
    private final Context context;

    public MoviesAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_movie, parent, false);
        ViewHolder vh = new MovieViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        Movie movie = movies.get(position);
        Picasso.with(context).load(MyConstant.IMAGE_BASE_URL + movie.getPosterPath()).into(movieViewHolder.imageViewMovie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addMovies(List<Movie> moviesList) {
        movies = moviesList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public static class MovieViewHolder extends ViewHolder {
        CardView cv;
        ImageView imageViewMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view_movie);
            imageViewMovie = (ImageView) itemView.findViewById(R.id.imageViewMovie);
        }

    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}

