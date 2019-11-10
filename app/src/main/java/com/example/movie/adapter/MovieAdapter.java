package com.example.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.api.RestApiManager;
import com.example.movie.model.MovieItem;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {
    private ArrayList<MovieItem> movieItems;
    private Context context;

    public MovieAdapter(ArrayList<MovieItem> movieItems, Context context) {
        this.movieItems = movieItems;
        this.context = context;
    }

    public void setMovieItems(ArrayList<MovieItem> movieItems) {
       this.movieItems = movieItems;
    }

    @NonNull
    @Override
    public MovieAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie,parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.Holder holder, int position) {
        MovieItem movie = movieItems.get(position);
        Glide.with(context).load(RestApiManager.BASE_MOVIE_IMAGE+movie.getPosterPath()).centerCrop().into(holder.img);
        String pos = String.valueOf(position+1);
        String releaseYear = movie.getReleaseDate().split("-")[0];
        String title = pos + ". " + movie.getTitle()+" ("+releaseYear+")";
        holder.txtTitle.setText(title);
        holder.txtRating.setText(String.valueOf(movie.getVoteAverage()));
        String ratingNumbers = " ("+movie.getVoteCount()+" votes)";
        holder.txtRatingNumbers.setText(ratingNumbers);
        String popularityNumbers = "  "+movie.getPopularity()+" \nmembers";
        holder.txtPopularityNumbers.setText(popularityNumbers);
        holder.txtOverview.setText(movie.getOverview());
        holder.contentBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://google.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView txtTitle;
        private final TextView txtRating;
        private final TextView txtRatingNumbers;
        private final TextView txtPopularityNumbers;
        private final TextView txtOverview;
        private final LinearLayout contentBox;


        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtRatingNumbers = itemView.findViewById(R.id.txtRatingNumbers);
            txtPopularityNumbers = itemView.findViewById(R.id.txtPopularityNumbers);
            txtOverview = itemView.findViewById(R.id.txtOverview);
            contentBox = itemView.findViewById(R.id.contentBox);
        }
    }
}
