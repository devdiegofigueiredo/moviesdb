package com.project.moviesdb.moviesdb.ui.movies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.moviesdb.moviesdb.BuildConfig;
import com.project.moviesdb.moviesdb.R;
import com.project.moviesdb.moviesdb.entities.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private static int LIMIT_PAGINATION = 5;
    private final int genresPosition;
    private int currentPage = 1;
    private boolean isLoading = false;

    public interface MovieClick {
        void onMovieClicked(Movie movie);
    }

    public interface LoadMovies {
        void loadMoreMovies(int page, String genreId, int genresPosition);
    }

    private List<Movie> movies = new ArrayList<>();
    private MovieClick callback;
    private LoadMovies callbackPagination;
    private final Context context;
    private String genreId;

    public MoviesAdapter(MovieClick callback, Context context, LoadMovies callbackPagination, String genreId, int genresPosition) {
        this.callback = callback;
        this.context = context;
        this.callbackPagination = callbackPagination;
        this.genreId = genreId;
        this.genresPosition = genresPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MoviesAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movies_item, viewGroup, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(BuildConfig.image_base_url + movies.get(position).getPosterPath()).into(holder.image);
        holder.popularity.setText(context.getString(R.string.views) + String.valueOf(movies.get(position).getPopularity()));
        holder.itemView.setOnClickListener(onMovieClicked(movies.get(position)));

        if (position == movies.size() - LIMIT_PAGINATION && !isLoading) {
            callbackPagination.loadMoreMovies(position, genreId, genresPosition);
            currentPage++;
            isLoading = true;
        }
    }

    private View.OnClickListener onMovieClicked(final Movie movie) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onMovieClicked(movie);
            }
        };
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
        isLoading = false;
        notifyDataSetChanged();
    }

    public String getGenreId() {
        return genreId;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView popularity;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            popularity = itemView.findViewById(R.id.popularity);
        }
    }
}
