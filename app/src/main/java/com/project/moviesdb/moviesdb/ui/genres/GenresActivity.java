package com.project.moviesdb.moviesdb.ui.genres;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.moviesdb.moviesdb.R;
import com.project.moviesdb.moviesdb.entities.Genre;
import com.project.moviesdb.moviesdb.util.LoadingUtil;

import java.util.List;

public class GenresActivity extends AppCompatActivity implements GenresContract.View, GenresAdapter.GenreClick {

    private AlertDialog loading;
    private LinearLayout erroView;
    private GenresPresenter presenter;
    private RecyclerView genresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        setupToolbar();

        loading = LoadingUtil.getDialog(getString(R.string.wait_search_genres), this);

        erroView = findViewById(R.id.error_view);
        genresList = findViewById(R.id.genres);

        GenresInteractor interactor = new GenresInteractor(this);
        presenter = new GenresPresenter(this, interactor);
        presenter.getGenres();
    }

    @Override
    public void showLoading() {
        if (loading != null && !loading.isShowing()) {
            loading.show();
        }
    }

    @Override
    public void hideLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

    @Override
    public void setupGenres(List<Genre> genres) {
        GenresAdapter adapter = new GenresAdapter(genres, this);

        if (genresList.getVisibility() == View.GONE) {
            genresList.setVisibility(View.VISIBLE);
        }

        genresList.setLayoutManager(new GridLayoutManager(this, 2));
        genresList.setAdapter(adapter);
    }

    @Override
    public void showErrorView(String message, String textButton) {
        erroView.setVisibility(View.VISIBLE);
        genresList.setVisibility(View.GONE);

        TextView messageLabel = findViewById(R.id.message);
        messageLabel.setText(message);

        Button button = findViewById(R.id.button);
        button.setText(textButton);
        button.setOnClickListener(onTryAgainClicked());
    }

    private View.OnClickListener onTryAgainClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erroView.setVisibility(View.GONE);
                genresList.setVisibility(View.VISIBLE);

                presenter.getGenres();
            }
        };
    }

    @Override
    public void onGenreClicked(String id) {

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.generic_toolbar);
        toolbar.setTitle(getString(R.string.genres));
        setSupportActionBar(toolbar);
    }
}
