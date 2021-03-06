package com.project.moviesdb.moviesdb.ui.genres;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.moviesdb.moviesdb.R;
import com.project.moviesdb.moviesdb.entities.Genre;
import com.project.moviesdb.moviesdb.entities.Movie;
import com.project.moviesdb.moviesdb.ui.movies.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> implements MoviesAdapter.MovieClick {

    interface GenreCallback {
        void loadMovies(String id, int position, int page);
    }

    interface MoviesCallback {
        void onMovieClicked(Movie movie);
    }

    private final List<Genre> genres;
    private final GenreCallback loadGenre;
    private final MoviesCallback movieClicked;
    private final Context context;
    private final List<MoviesAdapter> adapters = new ArrayList<>();
    private final MoviesAdapter.LoadMovies callbackPagination;

    GenresAdapter(List<Genre> genres, GenreCallback loadGenre,
                  MoviesCallback movieClicked,
                  Context context,
                  MoviesAdapter.LoadMovies callbackPagination) {
        this.genres = genres;
        this.context = context;
        this.loadGenre = loadGenre;
        this.movieClicked = movieClicked;
        this.callbackPagination = callbackPagination;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.genres_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (position >= adapters.size() && position <= genres.size()) {
            adapters.add(new MoviesAdapter(this, context, callbackPagination, genres.get(position).getId(), position));
            loadGenre.loadMovies(genres.get(position).getId(), position, 1);
        }

        viewHolder.genre.setText(genres.get(position).getName());
        viewHolder.movies.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        viewHolder.movies.setAdapter(adapters.get(position));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        movieClicked.onMovieClicked(movie);
    }

    public void addMovies(List<Movie> movies, int position) {
        adapters.get(position).addMovies(movies);
    }

    public void removeMovies(String genreId) {
        for (int i = 0; i < adapters.size(); i++) {
            if (adapters.get(i).getGenreId().equals(genreId)) {
                genres.remove(i);
                notifyDataSetChanged();
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView genre;
        RecyclerView movies;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            genre = itemView.findViewById(R.id.genre);
            movies = itemView.findViewById(R.id.movies);
        }
    }
}
