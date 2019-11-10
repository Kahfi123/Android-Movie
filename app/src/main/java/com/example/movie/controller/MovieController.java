package com.example.movie.controller;

import android.graphics.Movie;
import android.util.Log;
import android.view.View;

import com.example.movie.activity.MainActivity;
import com.example.movie.api.ApiService;
import com.example.movie.api.RestApiManager;
import com.example.movie.model.BasicResponse;
import com.example.movie.model.MovieItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieController {
    MainActivity view;
    ArrayList<MovieItem> movieItems;
    private static final String TAG = MovieController.class.getSimpleName();
    public MovieController(MainActivity view) {
        this.view = view;
        movieItems = new ArrayList<>();
    }
    public void updateView(){
        view.progressBar.setVisibility(View.GONE);
        view.recyclerView.setVisibility(View.VISIBLE);
        view.movieAdapter.setMovieItems(movieItems);
    }
    public void getTopRatedMovies(){
        ApiService apiService = RestApiManager.getInstance().getApiService();
        Call<BasicResponse> apiServiceCall = apiService.getTopRatedMovies(RestApiManager.BASE_MOVIE_API_KEY,1);
        apiServiceCall.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if(response.code()==200){
                    movieItems.addAll(response.body().getMovie());
                }
                updateView();
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.d(TAG, "FAILED : "+ t.getMessage());
                view.progressBar.setVisibility(View.GONE);
            }
        });
    }
}
