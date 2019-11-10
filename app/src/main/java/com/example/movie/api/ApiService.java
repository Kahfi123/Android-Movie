package com.example.movie.api;

import com.example.movie.model.BasicResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/top_rated")
    Call<BasicResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);
}
