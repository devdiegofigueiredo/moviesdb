package com.project.moviesdb.moviesdb.ui.genres;

import android.content.Context;
import android.support.annotation.NonNull;

import com.project.moviesdb.moviesdb.BuildConfig;
import com.project.moviesdb.moviesdb.R;
import com.project.moviesdb.moviesdb.data.remote.BaseService;
import com.project.moviesdb.moviesdb.ui.genres.GenresContract.Presenter.GenresCallback;
import com.project.moviesdb.moviesdb.ui.genres.entities.GenresResponse;

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
        Call<GenresResponse> callWeathers = BaseService.getGenresService().getGenres(BuildConfig.api_key);
        callWeathers.enqueue(callbackWeather(callback));
    }

    private Callback<GenresResponse> callbackWeather(final GenresCallback callback) {
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
}
