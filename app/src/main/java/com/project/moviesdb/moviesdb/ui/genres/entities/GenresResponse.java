package com.project.moviesdb.moviesdb.ui.genres.entities;

import com.project.moviesdb.moviesdb.entities.Genre;

import java.util.List;

public class GenresResponse {

    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
