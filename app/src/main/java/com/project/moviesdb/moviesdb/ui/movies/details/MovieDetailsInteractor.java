package com.project.moviesdb.moviesdb.ui.movies.details;

import com.google.gson.Gson;
import com.project.moviesdb.moviesdb.entities.Movie;

public class MovieDetailsInteractor implements MovieDetailsContract.Interactor {

    @Override
    public void convertMovieToObject(MovieDetailsContract.Presenter.MovieConvertedCallback callback, String jsonMovie) {
        Gson gson = new Gson();
        callback.onMovieConverted(gson.fromJson(jsonMovie, Movie.class));
    }
}
