package com.example.movie.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager {
    private ApiService apiService;
    public static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_MOVIE_API_KEY = "YOUR_API_KEY";
    public static final String BASE_MOVIE_IMAGE = "https://image.tmdb.org/t/p/w92";
    private static RestApiManager instance = null;

    public ApiService getApiService() {
        return apiService;
    }

    public static RestApiManager getInstance(){
        if(instance == null){
            instance = new RestApiManager();
        }
        return instance;
    }
    private RestApiManager() {
        buildRetrofit();
    }
    private void buildRetrofit(){
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_MOVIE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        this.apiService = retrofit.create(ApiService.class);
    }
}
