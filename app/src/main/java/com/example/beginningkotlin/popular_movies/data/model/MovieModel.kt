package com.example.beginningkotlin.popular_movies.data.model
import com.example.beginningkotlin.constants.NetworkConstants
import com.google.gson.annotations.SerializedName


data class MovieModel(
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("id")
    val id: Int

) {
    fun getFullImageURL() : String {
        return NetworkConstants.POSTERS_BASE_URL + posterPath
    }
}