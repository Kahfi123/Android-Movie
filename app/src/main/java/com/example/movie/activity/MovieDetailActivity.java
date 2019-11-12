package com.example.movie.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.movie.R;
import com.example.movie.controller.MovieDetailController;
import com.example.movie.model.MovieItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.w3c.dom.Text;


public class MovieDetailActivity extends AppCompatActivity {
    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    private AppCompatTextView mTitleTextView;
    public ImageView imgMovieBackdrop, imgMoviePoster;
    public TextView txtTitle, txtOverview, txtRatingNumbers, txtRating, txtGenres, txtRuntime;
    public ShimmerFrameLayout shimmeringContainer;
    public LinearLayout contentBox;
    public ImageView imgAdult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setupView();
        int movieId = getIntentData();
        MovieDetailController movieDetailController = new MovieDetailController(this, movieId);
        if(movieId == -1){
            Log.d(TAG, "Couldn't fetch movie detail with id :"+movieId);
        }
        movieDetailController.getMovieDetail();
    }

    private int getIntentData() {

        try{
            int movieId = getIntent().getIntExtra("movieId", -1);
            return movieId;
        }catch (NullPointerException error){
            Log.d(TAG, "ERROR : "+error);
            return -1;
        }

    }

    private void setupView() {
        setupActionBar();
        imgMovieBackdrop = findViewById(R.id.imgMovieBackdrop);
        imgMoviePoster = findViewById(R.id.imgMoviePoster);
        txtTitle = findViewById(R.id.txtTitle);
        txtOverview = findViewById(R.id.txtOverview);
        txtRating = findViewById(R.id.txtRating);
        txtRatingNumbers = findViewById(R.id.txtRatingNumbers);
        txtRuntime = findViewById(R.id.txtRuntime);
        txtGenres = findViewById(R.id.txtGenres);
        shimmeringContainer = findViewById(R.id.shimmerMovieContainer);
        imgAdult = findViewById(R.id.imgAdult);
        contentBox = findViewById(R.id.contentBox);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlack, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlack));
        }
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar==null){
            return;
        }
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBlack)));
        actionBar.setDisplayHomeAsUpEnabled(true);
        mTitleTextView = new AppCompatTextView(getApplicationContext());
        mTitleTextView.setSingleLine();
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        actionBar.setCustomView(mTitleTextView, layoutParams);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP);
        mTitleTextView.setTextAppearance(getApplicationContext(), android.R.style.TextAppearance_Medium);
        mTitleTextView.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
