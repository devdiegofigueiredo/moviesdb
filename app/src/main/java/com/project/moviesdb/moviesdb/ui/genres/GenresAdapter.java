package com.project.moviesdb.moviesdb.ui.genres;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.moviesdb.moviesdb.R;
import com.project.moviesdb.moviesdb.entities.Genre;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    interface GenreClick {
        void onGenreClicked(String id);
    }

    private final List<Genre> genres;
    private final GenreClick callback;

    GenresAdapter(List<Genre> genres, GenreClick genreCallback) {
        this.genres = genres;
        this.callback = genreCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.genres_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.genre.setText(genres.get(position).getName());
        viewHolder.container.setOnClickListener(onGenreClicked(genres.get(position).getId()));
    }

    private View.OnClickListener onGenreClicked(final String id) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onGenreClicked(id);
            }
        };
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView genre;
        CardView container;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            genre = itemView.findViewById(R.id.genre);
            container = itemView.findViewById(R.id.container);
        }
    }
}
