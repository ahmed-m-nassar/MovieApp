package com.example.beginningkotlin.popular_movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.beginningkotlin.base.BaseViewModel
import com.example.beginningkotlin.popular_movies.data.model.MovieModel
import com.example.beginningkotlin.popular_movies.data.repository.MovieDetailsRepository
import com.example.beginningkotlin.popular_movies.data.repository.PopularMoviesRepository

class PopularMoviesListViewModel : BaseViewModel<PopularMoviesRepository>() {

    fun getMovies() : LiveData<List<MovieModel>> {
        isLoading.value = true
        val result = repository.getPopularMovies()
        isLoading.value = false
        return result
    }

    override fun getRepositoryType(): PopularMoviesRepository {
        return PopularMoviesRepository()
    }
}