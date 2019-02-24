package com.project.moviesdb.moviesdb.ui.movies.details;

import com.project.moviesdb.moviesdb.entities.Movie;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter,
        MovieDetailsContract.Presenter.MovieConvertedCallback {

    private final MovieDetailsContract.View view;
    private final MovieDetailsContract.Interactor interactor;

    MovieDetailsPresenter(MovieDetailsContract.View view, MovieDetailsContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onMovieReceived(String jsonMovie) {
        interactor.convertMovieToObject(this, jsonMovie);
    }

    @Override
    public void onMovieConverted(Movie movie) {
        view.setupMovieDetails(movie);
    }
}
