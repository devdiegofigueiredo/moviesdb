package com.project.moviesdb.moviesdb.data.remote;

import com.project.moviesdb.moviesdb.BuildConfig;
import com.project.moviesdb.moviesdb.ui.movies.entities.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {

    @GET(BuildConfig.get_movies_by_gender_api)
    Call<MoviesResponse> getMovies(@Query("api_key") String apiKey,
                                   @Query("with_genres") String genre,
                                   @Query("page") String page);

}
