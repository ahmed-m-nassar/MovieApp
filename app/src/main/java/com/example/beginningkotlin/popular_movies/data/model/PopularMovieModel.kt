package com.example.beginningkotlin.popular_movies.data.model
import com.google.gson.annotations.SerializedName


data class PopularMovieModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int

)