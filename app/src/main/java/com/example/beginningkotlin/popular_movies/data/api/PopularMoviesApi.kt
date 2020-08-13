package com.example.beginningkotlin.popular_movies.data.api

import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.popular_movies.data.model.PopularMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesApi {
    @GET(NetworkConstants.POPULAR_RELATIVE_URL)
    suspend fun getMovies(@Query(NetworkConstants.API_KEY) apiKey : String) : Response<PopularMovieModel>
}