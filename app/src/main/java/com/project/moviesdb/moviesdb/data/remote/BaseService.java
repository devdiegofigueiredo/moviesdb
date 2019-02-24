package com.project.moviesdb.moviesdb.data.remote;

import com.project.moviesdb.moviesdb.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

    private static Retrofit instance;

    private static Retrofit getRetrofit(String baseUrl) {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return instance;
    }

    public static GenresService getGenresService() {
        return getRetrofit(BuildConfig.base_url).create(GenresService.class);
    }
}
