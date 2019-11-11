package com.example.movie.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.movie.R;
import com.example.movie.model.MovieItem;


public class MovieDetailActivity extends AppCompatActivity {
    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    private MovieItem movie;
    private AppCompatTextView mTitleTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        setupView();
    }

    private void getIntentData() {
        try{
            movie = (MovieItem) getIntent().getSerializableExtra("movie");
            mTitleTextView.setText(movie.getTitle());
        }catch (NullPointerException error){
            Log.d(TAG, "ERROR : "+error);
        }

    }

    private void setupView() {
        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar==null){
            return;
        }
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
