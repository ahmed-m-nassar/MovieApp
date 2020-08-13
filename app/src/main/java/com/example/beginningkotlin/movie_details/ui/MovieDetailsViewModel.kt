package com.example.beginningkotlin.movie_details.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.base.RetrofitBuilder
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.movie_details.data.api.MovieDetailsApi
import com.example.beginningkotlin.movie_details.data.model.MovieDetailsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MovieDetailsViewModel : ViewModel() {
    val movieDetails : MutableLiveData<MovieDetailsModel> = MutableLiveData()

    val isLoading : MutableLiveData<Boolean> = MutableLiveData()

    val message : MutableLiveData<String> = MutableLiveData()



    fun getMovieDetails(movieID : Int)   {
        //start loading
        isLoading.value = true

        val api = RetrofitBuilder.retrofitBuilder.build().create(MovieDetailsApi::class.java)

        GlobalScope
            .launch(Dispatchers.IO) {
                try {
                    val response = api.getMovieDetails(movieID,
                        NetworkConstants.API_KEY_VALUE)
                    if(response.isSuccessful) {
                        isLoading.postValue(false)
                        movieDetails.postValue(response.body())
                    } else {
                        isLoading.postValue(false)
                        message.postValue(response.message() + " Error code = " + response.code())
                    }
                } catch(exception : Exception) {
                    message.postValue(exception.message)
                    isLoading.postValue(false)
                }

            }

    }

}