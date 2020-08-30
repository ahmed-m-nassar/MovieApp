package com.example.beginningkotlin.data.response_model
import com.google.gson.annotations.SerializedName


data class PopularMovieResponseModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResponseModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int

)