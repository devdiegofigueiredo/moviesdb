package com.project.moviesdb.moviesdb.ui.genres;

import com.project.moviesdb.moviesdb.entities.Genre;

import java.util.List;

public interface GenresContract {

    interface View {
        void showLoading();

        void hideLoading();

        void setupGenres(List<Genre> genres);

        void showErrorView(String message, String textButton);
    }

    interface Presenter {
        interface GenresCallback {
            void onGenresSuccess(List<Genre> genres);

            void onGenresError(String message, String textButton);
        }

        void getGenres();
    }

    interface Interactor {
        void getGenres(Presenter.GenresCallback callback);
    }
}
