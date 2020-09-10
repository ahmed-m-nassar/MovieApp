package com.example.beginningkotlin.ui.popular_movies.model

import com.example.beginningkotlin.data.response_model.MovieResponseModel

class PopularMoviesUIModel(val posterURL : String,
                           val movieTitle : String,
                           val releaseDate : String,
                           val movieID : String
                           )  {
    companion object {
        fun convertResponseModel(responseModel : MovieResponseModel): PopularMoviesUIModel {
            val posterURL = responseModel.getFullImageURL()
            val movieTitle = responseModel.originalTitle
            val releaseDate = responseModel.releaseDate
            val movieID = responseModel.id.toString()

            return PopularMoviesUIModel(posterURL , movieTitle , releaseDate , movieID)
        }
    }
}