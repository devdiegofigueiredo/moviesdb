package com.project.moviesdb.moviesdb;

import com.project.moviesdb.moviesdb.entities.Genre;
import com.project.moviesdb.moviesdb.entities.Movie;
import com.project.moviesdb.moviesdb.ui.genres.GenresContract;
import com.project.moviesdb.moviesdb.ui.genres.GenresPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GenresPresenterTest {

    @Captor
    ArgumentCaptor<GenresContract.Presenter.ConvertMovieCallback> convertMovieCaptor =
            ArgumentCaptor.forClass(GenresContract.Presenter.ConvertMovieCallback.class);

    @Captor
    ArgumentCaptor<GenresContract.Presenter.GenresCallback> genresCaptor =
            ArgumentCaptor.forClass(GenresContract.Presenter.GenresCallback.class);

    @Captor
    ArgumentCaptor<GenresContract.Presenter.MoviesCallback> movieCaptor =
            ArgumentCaptor.forClass(GenresContract.Presenter.MoviesCallback.class);

    @Mock
    private GenresContract.View view;

    @Mock
    private GenresContract.Interactor interactor;

    private GenresPresenter presenter;

    @Before
    public void setup() {
        presenter = new GenresPresenter(view, interactor);
    }

    @Test
    public void getGenresSuccessTest() {
        presenter.getGenres();
        Mockito.verify(view).showLoading();
        Mockito.verify(interactor).getGenres(genresCaptor.capture());

        List<Genre> genres = new ArrayList<>();
        genresCaptor.getValue().onGenresSuccess(genres);

        Mockito.verify(view).hideLoading();
        Mockito.verify(view).setupGenres(genres);
    }

    @Test
    public void getGenresFailureTest() {
        presenter.getGenres();
        Mockito.verify(view).showLoading();
        Mockito.verify(interactor).getGenres(genresCaptor.capture());

        genresCaptor.getValue().onGenresError("message", "textButton");

        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showErrorView("message", "textButton");
    }

    @Test
    public void getMoviesSuccessTest() {
        String id = "id";
        int position = 1;
        int page = 1;

        presenter.getMovies(id, position, page);

        Mockito.verify(interactor).getMovies(movieCaptor.capture(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt());

        List<Movie> movies = new ArrayList<>();
        movieCaptor.getValue().onMoviesSuccess(movies, 1);

        Mockito.verify(view).addMovies(movies, position);
    }


    @Test
    public void getMoviesFailureTest() {
        String id = "id";
        int position = 1;
        int page = 1;

        presenter.getMovies(id, position, page);

        Mockito.verify(interactor).getMovies(movieCaptor.capture(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt());

        movieCaptor.getValue().onMoviesError(id);

        Mockito.verify(view).hideGenres(Mockito.anyString());
    }

    @Test
    public void convertMovieToJson() {
        Movie movie = new Movie();
        presenter.onMovieClicked(movie);

        Mockito.verify(interactor).convetMovieToText(convertMovieCaptor.capture(), Mockito.any(Movie.class));
        convertMovieCaptor.getValue().convertedMovieToText("json");
        Mockito.verify(view).startDetailsMovie("json");
    }
}
