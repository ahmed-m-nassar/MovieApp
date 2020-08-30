package com.example.beginningkotlin.data.api

import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.data.response_model.MovieDetailsResponseModel
import com.example.beginningkotlin.data.response_model.PopularMovieResponseModel
import com.example.beginningkotlin.movie_details.model.RatingBody
import com.example.beginningkotlin.data.response_model.RatingResponseModel
import retrofit2.Response
import retrofit2.http.*

interface MovieAPI {
    @GET("{" + NetworkConstants.ID_RELATIVE_URL + "}")
    suspend fun getMovieDetails(@Path(NetworkConstants.ID_RELATIVE_URL) movieID : Int,
                                @Query(NetworkConstants.API_KEY) apiKey : String ) : Response<MovieDetailsResponseModel>

    @GET(NetworkConstants.POPULAR_RELATIVE_URL)
    suspend fun getMovies(@Query(NetworkConstants.API_KEY) apiKey : String) : Response<PopularMovieResponseModel>

    @POST("{" + NetworkConstants.ID_RELATIVE_URL + "}" + "/" + NetworkConstants.RATE_RELATIVE_URL)
    suspend fun rateMovie(@Body body : RatingBody,
                          @Path(NetworkConstants.ID_RELATIVE_URL) movieID : Int,
                          @Query(NetworkConstants.API_KEY) apiKey : String,
                          @Query(NetworkConstants.GUEST_SESSION_ID_KEY) guestSessionID : String ) : Response<RatingResponseModel>
}