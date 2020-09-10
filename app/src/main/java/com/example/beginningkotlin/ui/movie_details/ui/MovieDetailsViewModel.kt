package com.example.beginningkotlin.ui.movie_details.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.beginningkotlin.ui.base.BaseViewModel
import com.example.beginningkotlin.ui.movie_details.model.MovieDetailsUIModel
import com.example.beginningkotlin.ui.popular_movies.data.repository.MovieDetailsRepository
import com.example.beginningkotlin.ui.movie_details.model.RatingBody

class MovieDetailsViewModel(repository: MovieDetailsRepository) :
    BaseViewModel<MovieDetailsRepository>(
        repository
    ) {
    var movieDetails: LiveData<MovieDetailsUIModel>? = null
    var rate= MutableLiveData<String>()
    fun getMovieDetails(movieID: Int) {
        isLoading.value = true
        val result = repository.getMovieDetails(movieID)
        movieDetails = Transformations.switchMap(result) {
            val transformedPopularMovies =
                MovieDetailsUIModel.convertResponseModel(it)
            isLoading.value = false
            MutableLiveData(transformedPopularMovies)
        }

    }

    var ratingSucceed: LiveData<Boolean>? = null

    fun rateMovie(movieID: Int) {
        isLoading.value = true
        Log.d("RatingViewModel", "About to rate the movie")
        val result = repository.rateMovie(
            movieID,
            RatingBody(rate!!.value!!.toFloat())
        )
        ratingSucceed = Transformations.switchMap(result) {
            Log.d("RatingViewModel", "Rating is done")
            isLoading.value = false
            MutableLiveData(true)
        }
    }


}