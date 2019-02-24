package com.project.moviesdb.moviesdb.data.remote;

import com.project.moviesdb.moviesdb.ui.genres.entities.GenresResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenresService {

    @GET("genre/movie/list")
    Call<GenresResponse> getGenres(@Query("api_key") String apiKey);

}
