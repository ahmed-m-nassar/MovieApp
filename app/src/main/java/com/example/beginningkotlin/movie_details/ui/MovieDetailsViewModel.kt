package com.example.beginningkotlin.movie_details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.base.BaseViewModel
import com.example.beginningkotlin.base.RetrofitBuilder
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.movie_details.data.api.MovieDetailsApi
import com.example.beginningkotlin.movie_details.data.model.MovieDetailsModel
import com.example.beginningkotlin.popular_movies.data.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MovieDetailsViewModel : BaseViewModel<MovieDetailsRepository>() {

    fun getMovieDetails(movieID : Int) : LiveData<MovieDetailsModel>   {
        isLoading.value = true
        val result = repository.getMovieDetails(movieID)
        isLoading.value = false
        return result
    }

    override fun getRepositoryType(): MovieDetailsRepository {
        return MovieDetailsRepository()
    }

}