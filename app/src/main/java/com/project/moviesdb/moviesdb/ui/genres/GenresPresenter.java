package com.project.moviesdb.moviesdb.ui.genres;

import com.project.moviesdb.moviesdb.entities.Genre;
import com.project.moviesdb.moviesdb.entities.Movie;

import java.util.List;

public class GenresPresenter implements GenresContract.Presenter,
        GenresContract.Presenter.GenresCallback,
        GenresContract.Presenter.MoviesCallback,
        GenresContract.Presenter.ConvertMovieCallback {

    private final GenresContract.View view;
    private final GenresContract.Interactor interactor;

    GenresPresenter(GenresContract.View view, GenresContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getGenres() {
        view.showLoading();
        interactor.getGenres(this);
    }

    @Override
    public void getMovies(String id, int position, int page) {
        interactor.getMovies(this, id, position, page);
    }

    @Override
    public void onMovieClicked(Movie movie) {
        interactor.convetMovieToText(this, movie);
    }

    @Override
    public void onGenresSuccess(List<Genre> genres) {
        view.hideLoading();
        view.setupGenres(genres);
    }

    @Override
    public void onGenresError(String message, String textButton) {
        view.hideLoading();
        view.showErrorView(message, textButton);
    }

    @Override
    public void onMoviesSuccess(List<Movie> movies, int position) {
        view.addMovies(movies, position);
    }

    @Override
    public void onMoviesError() {

    }

    @Override
    public void convertedMovieToText(String jsonMovie) {
        view.startDetailsMovie(jsonMovie);
    }
}
