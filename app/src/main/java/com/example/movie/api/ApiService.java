package com.example.movie.api;

import com.example.movie.model.BasicResponse;
import com.example.movie.model.MovieItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/top_rated")
    Call<BasicResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);
    @GET("movie/{movie_id}")
    Call<MovieItem> getMovieDetail(@Path("movie_id") int movieId, @Query("api_key") String apiKey );
}
