package com.example.beginningkotlin.movie_details.data.api

import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.movie_details.data.model.MovieDetailsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsApi {
    @GET("{" + NetworkConstants.ID_RELATIVE_URL + "}")
    suspend fun getMovieDetails(@Path(NetworkConstants.ID_RELATIVE_URL) movieID : Int , @Query(NetworkConstants.API_KEY) apiKey : String) : Response<MovieDetailsModel>
}