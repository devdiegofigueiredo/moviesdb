package com.project.moviesdb.moviesdb.ui.genres;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.project.moviesdb.moviesdb.BuildConfig;
import com.project.moviesdb.moviesdb.R;
import com.project.moviesdb.moviesdb.data.remote.BaseService;
import com.project.moviesdb.moviesdb.entities.Movie;
import com.project.moviesdb.moviesdb.ui.genres.GenresContract.Presenter.GenresCallback;
import com.project.moviesdb.moviesdb.ui.genres.GenresContract.Presenter.MoviesCallback;
import com.project.moviesdb.moviesdb.ui.genres.entities.GenresResponse;
import com.project.moviesdb.moviesdb.ui.movies.entities.MoviesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresInteractor implements GenresContract.Interactor {

    private final Context context;

    GenresInteractor(Context context) {
        this.context = context;
    }

    @Override
    public void getGenres(GenresCallback callback) {
        Call<GenresResponse> callGenres = BaseService.getGenresService().getGenres(BuildConfig.api_key);
        callGenres.enqueue(callbackGenres(callback));
    }

    @Override
    public void getMovies(MoviesCallback callback, String genreId, int position, int page) {
        Call<MoviesResponse> callMovies = BaseService.getMoviesService().getMovies(BuildConfig.api_key, genreId, String.valueOf(page));
        callMovies.enqueue(callbackMovies(callback, position, genreId));
    }

    @Override
    public void convetMovieToText(GenresContract.Presenter.ConvertMovieCallback callback, Movie movie) {
        Gson gson = new Gson();
        callback.convertedMovieToText(gson.toJson(movie));
    }

    private Callback<GenresResponse> callbackGenres(final GenresCallback callback) {
        return new Callback<GenresResponse>() {

            @Override
            public void onResponse(@NonNull Call<GenresResponse> call, @NonNull Response<GenresResponse> response) {
                if (response.body() != null &&
                        response.body().getGenres() != null &&
                        !response.body().getGenres().isEmpty()) {
                    callback.onGenresSuccess(response.body().getGenres());
                } else {
                    callback.onGenresError(context.getString(R.string.no_gender_found), context.getString(R.string.try_again));
                }
            }

            @Override
            public void onFailure(Call<GenresResponse> call, Throwable t) {
                callback.onGenresError(t.getMessage(), context.getString(R.string.try_again));
            }
        };
    }

    private Callback<MoviesResponse> callbackMovies(final MoviesCallback callback, final int position, final String genreId) {
        return new Callback<MoviesResponse>() {

            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response.body() != null &&
                        response.body().getResults() != null &&
                        !response.body().getResults().isEmpty()) {
                    callback.onMoviesSuccess(response.body().getResults(), position);
                } else {
                    callback.onMoviesError(genreId);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                callback.onMoviesError(genreId);
            }
        };
    }
}
