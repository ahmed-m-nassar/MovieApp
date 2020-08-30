package com.example.beginningkotlin.popular_movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.beginningkotlin.base.BaseViewModel
import com.example.beginningkotlin.popular_movies.model.PopularMoviesUIModel
import com.example.beginningkotlin.data.repository.PopularMoviesRepository

class PopularMoviesListViewModel : BaseViewModel<PopularMoviesRepository>() {

    var popularMovies : LiveData<List<PopularMoviesUIModel>>? = null
    fun getMovies() {
        isLoading.value = true
        val result = repository.getPopularMovies()
        popularMovies = Transformations.switchMap(result) {
            isLoading.value = false
            MutableLiveData(it.map {
                PopularMoviesUIModel.convertResponseModel(it)
            })
        }
    }


    override fun getRepositoryType(): PopularMoviesRepository {
        return PopularMoviesRepository()
    }
}