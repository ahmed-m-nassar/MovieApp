package com.example.beginningkotlin.popular_movies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.base.RetrofitBuilder
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.popular_movies.data.api.PopularMoviesApi
import com.example.beginningkotlin.popular_movies.data.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class PopularMoviesListViewModel : ViewModel() {

    val movies : MutableLiveData<List<MovieModel>> = MutableLiveData()

    val isLoading : MutableLiveData<Boolean> = MutableLiveData()

    val message : MutableLiveData<String> = MutableLiveData()



    fun getMovies()   {
        //start loading
        isLoading.value = true


        val api = RetrofitBuilder.retrofitBuilder.build().create(PopularMoviesApi::class.java)


        GlobalScope
            .launch(Dispatchers.IO) {
            try {
                val response = api.getMovies(NetworkConstants.API_KEY_VALUE)
                if(response.isSuccessful) {
                    isLoading.postValue(false)
                    movies.postValue(response.body()?.results)
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