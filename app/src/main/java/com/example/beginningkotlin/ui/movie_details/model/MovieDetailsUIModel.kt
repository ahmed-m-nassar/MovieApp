package com.example.beginningkotlin.ui.movie_details.model

import com.example.beginningkotlin.data.response_model.MovieDetailsResponseModel

class MovieDetailsUIModel(
    val posterURL : String ,
    val movieTitle : String ,
    val releaseDate : String ,
    val rate : String ,
    val budget : String ,
    val revenue : String
) {
    companion object {
        fun convertResponseModel(movieDetails : MovieDetailsResponseModel): MovieDetailsUIModel {
            val posterURL = movieDetails.getFullImageURL()
            val movieTitle = movieDetails.originalTitle
            val releaseDate = movieDetails.releaseDate
            val rate = movieDetails.voteAverage.toString()
            val budget = movieDetails.budget.toString()
            val revenue = "5000"

            return MovieDetailsUIModel(posterURL , movieTitle , releaseDate , rate , budget , revenue)
        }
    }
}