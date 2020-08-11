package com.example.beginningkotlin.movie_details.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.Constants
import com.example.beginningkotlin.movie_details.data.api.MovieDetailsApi
import com.example.beginningkotlin.movie_details.data.model.MovieDetailsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailsViewModel : ViewModel() {
    val movieDetails : MutableLiveData<MovieDetailsModel> = MutableLiveData()

    val isLoading : MutableLiveData<Boolean> = MutableLiveData()

    val message : MutableLiveData<String> = MutableLiveData()



    fun getMovieDetails(movieID : Int)   {
        //start loading
        isLoading.value = true

        val retrofit : Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val  jsonPlaceHolder : MovieDetailsApi = retrofit.create(MovieDetailsApi::class.java)

        val call : Call<MovieDetailsModel> = jsonPlaceHolder.getMovieDetails(  movieID,Constants.API_KEY_VALUE )

        call.enqueue(object : Callback<MovieDetailsModel> {
            override fun onResponse(call: Call<MovieDetailsModel>, response: Response<MovieDetailsModel>) {
                isLoading.value = false
                if (response.isSuccessful){
                    movieDetails.setValue(response.body())
                } else {
                    val statusCode  = response.code();
                    message.value = "Error with code " + statusCode
                }

            }
            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {

                isLoading.value = false
                message.value = t.message
            }
        })
    }

}