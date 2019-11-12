package com.example.movie.controller;

import android.text.Html;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.activity.MovieDetailActivity;
import com.example.movie.api.ApiService;
import com.example.movie.api.RestApiManager;
import com.example.movie.helper.HelperUtil;
import com.example.movie.model.GenresItem;
import com.example.movie.model.MovieItem;
import com.example.movie.model.ProductionCompaniesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailController {
    private MovieItem movie;
    private MovieDetailActivity view;
    private static final String TAG = MovieDetailController.class.getSimpleName();
    public MovieDetailController(MovieDetailActivity view, int movieId) {
        this.view = view;
        movie = new MovieItem();
        movie.setId(movieId);
    }
    public void getMovieDetail(){
        ApiService apiService = RestApiManager.getInstance().getApiService();
        Call<MovieItem> call = apiService.getMovieDetail(movie.getId(), RestApiManager.BASE_MOVIE_API_KEY);
        call.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                if(response.code()==200){
                    movie = response.body();
                    updateView();
                }
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {
                Log.d(TAG, "FAILED : "+ t.getMessage());
            }
        });
    }

    private void updateView() {
        if(view==null){
            return;
        }
        Glide.with(view).load(RestApiManager.BASE_MOVIE_IMAGE_w500+movie.getBackdropPath()).centerCrop().into(view.imgMovieBackdrop);
        Glide.with(view).load(RestApiManager.BASE_MOVIE_IMAGE_w92+movie.getPosterPath()).centerCrop().into(view.imgMoviePoster);
        String year = "("+HelperUtil.convertToYear(movie.getReleaseDate())+")";
        view.txtTitle.setText(Html.fromHtml("<font color=#000000>"+movie.getTitle()+"</font> <font color=#808080><small>"+year+"</small></font>"));
        view.txtOverview.setText(movie.getOverview());
        view.txtRating.setText(String.valueOf(movie.getVoteAverage()));
        String ratingNumbers = movie.getVoteCount()+" votes";
        view.txtRatingNumbers.setText(ratingNumbers);
        view.shimmeringContainer.stopShimmer();
        view.shimmeringContainer.setVisibility(View.GONE);
        view.contentBox.setVisibility(View.VISIBLE);
        if(movie.isAdult()){
            view.imgAdult.setImageDrawable(view.getResources().getDrawable(R.drawable.adults));
        }else{
            view.imgAdult.setImageDrawable(view.getResources().getDrawable(R.drawable.general));
        }
        String runtime = movie.getRuntime()+" min";
        view.txtRuntime.setText(runtime);
        String genres = "";
        int i = 0;
        for(GenresItem genre : movie.getGenres()){
            genres += genre.getName();
            if(i != movie.getGenres().size()-1){
                genres += ", ";
            }
            i++;
        }
        view.txtGenres.setText(genres);
        view.txtReleaseDate.setText(movie.getReleaseDate());
        view.txtLanguage.setText(movie.getSpokenLanguages().get(0).getName());
        String prodCompanies = "";
        i = 0;
        for(ProductionCompaniesItem prodCompany : movie.getProductionCompanies()){
            prodCompanies += prodCompany.getName();
            if(i != movie.getProductionCompanies().size()-1){
                prodCompanies += ", ";
            }
            i++;
        }
        view.txtProductionCompanies.setText(prodCompanies);
        view.txtCountry.setText(movie.getProductionCountries().get(0).getName());

    }
}
