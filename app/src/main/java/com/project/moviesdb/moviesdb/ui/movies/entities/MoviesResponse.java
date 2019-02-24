package com.project.moviesdb.moviesdb.ui.movies.entities;

import com.google.gson.annotations.SerializedName;
import com.project.moviesdb.moviesdb.entities.Movie;

import java.util.List;

public class MoviesResponse {

    private String page;
    private List<Movie> results;

    @SerializedName("total_pages")
    private String totalPages;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }
}
