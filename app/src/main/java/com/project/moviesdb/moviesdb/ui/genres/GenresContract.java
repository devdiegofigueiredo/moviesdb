package com.project.moviesdb.moviesdb.ui.genres;

import com.project.moviesdb.moviesdb.entities.Genre;
import com.project.moviesdb.moviesdb.entities.Movie;

import java.util.List;

public interface GenresContract {

    interface View {
        void showLoading();

        void hideLoading();

        void setupGenres(List<Genre> genres);

        void showErrorView(String message, String textButton);

        void addMovies(List<Movie> movies, int position);

        void startDetailsMovie(String jsonMovie);
    }

    interface Presenter {

        interface GenresCallback {
            void onGenresSuccess(List<Genre> genres);

            void onGenresError(String message, String textButton);
        }

        interface MoviesCallback {
            void onMoviesSuccess(List<Movie> movies, int position);

            void onMoviesError();
        }

        interface ConvertMovieCallback {
            void convertedMovieToText(String jsonMovie);
        }

        void getGenres();

        void getMovies(String id, int position);

        void onMovieClicked(Movie movie);
    }

    interface Interactor {
        void getGenres(Presenter.GenresCallback callback);

        void getMovies(Presenter.MoviesCallback callback, String genreId, int position);

        void convetMovieToText(Presenter.ConvertMovieCallback callback, Movie movie);
    }
}
