package com.example.beginningkotlin.popular_movies.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.beginningkotlin.data.repository.base.BaseRepository
import com.example.beginningkotlin.network.Resource
import com.example.beginningkotlin.network.RetrofitBuilder
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.data.api.MovieAPI
import com.example.beginningkotlin.data.response_model.MovieDetailsResponseModel
import com.example.beginningkotlin.movie_details.model.RatingBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailsRepository : BaseRepository() {
    val api = RetrofitBuilder.retrofitBuilder.build().create(MovieAPI::class.java)

     fun getMovieDetails (movieID : Int) : LiveData<MovieDetailsResponseModel> {
         val responseMutableLiveData : MutableLiveData<MovieDetailsResponseModel> = MutableLiveData()
         GlobalScope.launch(Dispatchers.Main) {
             lateinit var resource : Resource<LiveData<MovieDetailsResponseModel>>
             try {
                 val response = api.getMovieDetails(movieID , NetworkConstants.API_KEY_VALUE)
                 if(response.isSuccessful) {
                     val movieDetails : LiveData<MovieDetailsResponseModel> = MutableLiveData(response.body())
                     resource = Resource(
                         Resource.Status.SUCCESS,
                         movieDetails,
                         null
                     )
                 } else {
                     resource = Resource(
                         Resource.Status.ERROR,
                         null
                         ,
                         response.message() + " Error code = " + response.code()
                     )
                 }

             } catch(exception : Exception) {
                 resource = Resource(
                     Resource.Status.ERROR,
                     null
                     ,
                     exception.message
                 )
             }

             if (resource.status == Resource.Status.SUCCESS){
                 responseMutableLiveData.value = resource.data?.value
             }else{
                 errorMessage.value = resource.message
             }
         }
         return responseMutableLiveData

    }

    fun rateMovie (movieID : Int , body : RatingBody) : LiveData<Boolean> {
        val responseMutableLiveData : MutableLiveData<Boolean> = MutableLiveData()
        GlobalScope.launch(Dispatchers.Main) {
            lateinit var resource : Resource<LiveData<Boolean>>
            try {
                val response = api.rateMovie(body,movieID,NetworkConstants.API_KEY_VALUE,NetworkConstants.GUEST_SESSION_ID_VALUE)
                if(response.isSuccessful) {
                    resource = Resource(
                        Resource.Status.SUCCESS,
                        null,
                        null
                    )
                    Log.d("RatingRepository","Rating is Successful")

                } else {
                    resource =
                        Resource(
                            Resource.Status.ERROR,
                            null
                            ,
                            response.message() + " Error code = " + response.code()
                        )
                    Log.d("RatingRepository","Rating failed " +  response.raw().request().url())

                }

            } catch(exception : Exception) {
                resource = Resource(
                    Resource.Status.ERROR,
                    null
                    ,
                    exception.message
                )
                Log.d("RatingRepository","Exception in Rating")

            }

            if (resource.status == Resource.Status.SUCCESS){
                responseMutableLiveData.value = true
            }else{
                // livedata of error message you will observe  on it on base activity and view message in toast
                errorMessage.value = resource.message
            }
        }
        return responseMutableLiveData

    }
}