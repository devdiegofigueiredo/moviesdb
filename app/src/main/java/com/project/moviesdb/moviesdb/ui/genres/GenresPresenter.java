package com.project.moviesdb.moviesdb.ui.genres;

import com.project.moviesdb.moviesdb.entities.Genre;

import java.util.List;

public class GenresPresenter implements GenresContract.Presenter,
        GenresContract.Presenter.GenresCallback {

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
    public void onGenresSuccess(List<Genre> genres) {
        view.hideLoading();
        view.setupGenres(genres);
    }

    @Override
    public void onGenresError(String message, String textButton) {
        view.hideLoading();
        view.showErrorView(message, textButton);
    }
}
