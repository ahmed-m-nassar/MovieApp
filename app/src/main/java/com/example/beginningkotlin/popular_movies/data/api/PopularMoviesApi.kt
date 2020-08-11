package com.example.beginningkotlin.popular_movies.data.api

import com.example.beginningkotlin.Constants
import com.example.beginningkotlin.popular_movies.data.model.PopularMovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesApi {
    @GET(Constants.POPULAR_RELATIVE_URL)
    fun getMovies(@Query(Constants.API_KEY) apiKey : String) : Call<PopularMovieModel>;
}