package com.example.movie.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class BasicResponse{

	@SerializedName("results")
	private List<MovieItem> movie;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("total_results")
	private int totalResults;

	public void setMovie(List<MovieItem> movie){
		this.movie = movie;
	}

	public List<MovieItem> getMovie(){
		return movie;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
 	public String toString(){
		return 
			"BasicResponse{" + 
			"movie = '" + movie + '\'' + 
			",page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}