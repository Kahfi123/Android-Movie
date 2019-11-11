package com.example.movie.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Movie;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.movie.R;
import com.example.movie.adapter.MovieAdapter;
import com.example.movie.controller.MovieController;
import com.example.movie.listener.EndlessRecyclerViewScrollListener;
import com.example.movie.model.MovieItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ProgressBar progressBar;
    public SwipeRefreshLayout swipeRefreshLayout;
    public MovieAdapter movieAdapter;
    public EndlessRecyclerViewScrollListener scrollListener;
    public ShimmerFrameLayout shimmerFrameLayout;
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
        shimmerFrameLayout = findViewById(R.id.shimmerMovieContainer);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                movieController.resetRecyclerViewState();
                movieController.getTopRatedMovies(1);
                shimmerFrameLayout.startShimmer();
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        movieAdapter = new MovieAdapter(new ArrayList<MovieItem>(), this);
        recyclerView.setAdapter(movieAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Top Rated Movies");
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBlack)));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlack, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlack));
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
