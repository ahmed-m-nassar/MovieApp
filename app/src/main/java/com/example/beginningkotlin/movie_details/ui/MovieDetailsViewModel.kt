package com.example.beginningkotlin.movie_details.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.base.BaseViewModel
import com.example.beginningkotlin.base.RetrofitBuilder
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.movie_details.data.api.MovieDetailsApi
import com.example.beginningkotlin.movie_details.data.model.MovieDetailsModel
import com.example.beginningkotlin.movie_details.data.model.ui_model.MovieDetailsUIModel
import com.example.beginningkotlin.popular_movies.data.model.ui_model.PopularMoviesUIModel
import com.example.beginningkotlin.popular_movies.data.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MovieDetailsViewModel : BaseViewModel<MovieDetailsRepository>() {
    var movieDetails : LiveData<MovieDetailsUIModel>? = null

    fun getMovieDetails(movieID : Int)    {
        isLoading.value = true
        val result = repository.getMovieDetails(movieID)
        movieDetails = Transformations.switchMap(result) {
            val transformedPopularMovies :MovieDetailsUIModel = MovieDetailsUIModel (
            it.getFullImageURL(),
            it.originalTitle,
            it.releaseDate,
            it.voteAverage.toString(),
            it.budget.toString(),
            "50000"
            )
            isLoading.value = false
            MutableLiveData(transformedPopularMovies)
            }

        }


    override fun getRepositoryType(): MovieDetailsRepository {
        return MovieDetailsRepository()
    }

}