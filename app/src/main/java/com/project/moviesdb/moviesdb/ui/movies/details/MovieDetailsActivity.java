package com.project.moviesdb.moviesdb.ui.movies.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.moviesdb.moviesdb.BuildConfig;
import com.project.moviesdb.moviesdb.R;
import com.project.moviesdb.moviesdb.entities.Movie;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    public static String EXTRA_JSON_MOVIE = "extra_json_movie";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setupToolbar();

        MovieDetailsInteractor interactor = new MovieDetailsInteractor();
        MovieDetailsPresenter presenter = new MovieDetailsPresenter(this, interactor);
        presenter.onMovieReceived(Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_JSON_MOVIE));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupMovieDetails(Movie movie) {
        ImageView image = findViewById(R.id.image);
        Picasso.get().load(BuildConfig.image_base_url + movie.getPosterPath()).into(image);

        TextView title = findViewById(R.id.title);
        title.setText(movie.getTitle());

        TextView overview = findViewById(R.id.overview);
        overview.setText(movie.getOverview());

        TextView popularity = findViewById(R.id.popularity);
        popularity.setText(getString(R.string.popularity) + String.valueOf(movie.getPopularity()));

        TextView releaseDate = findViewById(R.id.release_date);
        releaseDate.setText(getString(R.string.release_date) +movie.getReleaseDate());
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.generic_toolbar);
        toolbar.setTitle(R.string.movie_details);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}
