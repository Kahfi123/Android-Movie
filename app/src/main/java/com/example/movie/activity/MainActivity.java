package com.example.movie.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.movie.R;
import com.example.movie.adapter.MovieAdapter;
import com.example.movie.controller.MovieController;
import com.example.movie.model.MovieItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ProgressBar progressBar;
    public MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MovieController movieController = new MovieController(this);
        setupView();
        movieController.getTopRatedMovies();
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyvlerView);
        recyclerView.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progressBar);
        movieAdapter = new MovieAdapter(new ArrayList<MovieItem>(), getApplicationContext());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Top Rated Movies");
        }
    }
}
