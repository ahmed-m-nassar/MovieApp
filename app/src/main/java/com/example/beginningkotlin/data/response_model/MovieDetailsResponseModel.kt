package com.example.beginningkotlin.data.response_model
import com.example.beginningkotlin.constants.NetworkConstants
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET


data class MovieDetailsResponseModel(
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int

) {
    fun getFullImageURL() : String {
        return NetworkConstants.POSTERS_BASE_URL + posterPath
    }
}

