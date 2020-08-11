package com.example.beginningkotlin.movie_details.data.api

import com.example.beginningkotlin.Constants
import com.example.beginningkotlin.movie_details.data.model.MovieDetailsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsApi {
    @GET("{id}")
    fun getMovieDetails(@Path("id") movieID : Int , @Query(Constants.API_KEY) apiKey : String) : Call<MovieDetailsModel>;
}