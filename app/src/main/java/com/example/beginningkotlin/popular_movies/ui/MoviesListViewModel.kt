package com.example.beginningkotlin.popular_movies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.Constants
import com.example.beginningkotlin.popular_movies.data.api.PopularMoviesApi
import com.example.beginningkotlin.popular_movies.data.model.MovieModel
import com.example.beginningkotlin.popular_movies.data.model.PopularMovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesListViewModel : ViewModel() {

    val movies : MutableLiveData<List<MovieModel>> = MutableLiveData()

    val isLoading : MutableLiveData<Boolean> = MutableLiveData()

    val message : MutableLiveData<String> = MutableLiveData()



    fun getMovies()   {
        //start loading
        isLoading.value = true

        val retrofit : Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val  jsonPlaceHolder : PopularMoviesApi = retrofit.create(PopularMoviesApi::class.java)

        val call : Call<PopularMovieModel> = jsonPlaceHolder.getMovies( Constants.API_KEY_VALUE)

        call.enqueue(object : Callback<PopularMovieModel> {
            override fun onResponse(call: Call<PopularMovieModel>, response: Response<PopularMovieModel>) {
                isLoading.value = false
                if (response.isSuccessful){
                    movies.setValue(response.body()?.results)
                } else {
                    val statusCode  = response.code();
                    message.value = "Error with code " + statusCode
                }

            }
            override fun onFailure(call: Call<PopularMovieModel>, t: Throwable) {

                isLoading.value = false
                message.value = t.message
            }
        })
    }

}