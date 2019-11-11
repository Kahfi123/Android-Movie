package com.example.movie.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.movie.R;
import com.example.movie.adapter.MovieAdapter;
import com.example.movie.controller.MovieController;
import com.example.movie.listener.EndlessRecyclerViewScrollListener;
import com.example.movie.model.MovieItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ProgressBar progressBar;
    public SwipeRefreshLayout swipeRefreshLayout;
    public MovieAdapter movieAdapter;
    public EndlessRecyclerViewScrollListener scrollListener;
    private MovieController movieController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        movieController = new MovieController(this);
        movieController.getTopRatedMovies(1);
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyvlerView);
        recyclerView.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                movieController.resetRecyclerViewState();
                movieController.getTopRatedMovies(1);
            }
        });
        movieAdapter = new MovieAdapter(new ArrayList<MovieItem>(), this);
        recyclerView.setAdapter(movieAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Top Rated Movies");
        }
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                progressBar.setVisibility(View.VISIBLE);
                movieController.getTopRatedMovies(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
    }


}
