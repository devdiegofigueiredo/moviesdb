package com.project.moviesdb.moviesdb.ui.movies.details;

import com.project.moviesdb.moviesdb.entities.Movie;

public interface MovieDetailsContract {

    interface View {
        void setupMovieDetails(Movie movie);
    }

    interface Presenter {
        interface MovieConvertedCallback {
            void onMovieConverted(Movie movie);
        }

        void onMovieReceived(String jsonMovie);
    }

    interface Interactor {
        void convertMovieToObject(Presenter.MovieConvertedCallback callback, String jsonMovie);
    }
}
