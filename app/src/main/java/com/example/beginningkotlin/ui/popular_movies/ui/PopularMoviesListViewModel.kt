package com.example.beginningkotlin.ui.popular_movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.beginningkotlin.ui.base.BaseViewModel
import com.example.beginningkotlin.ui.popular_movies.model.PopularMoviesUIModel
import com.example.beginningkotlin.data.repository.PopularMoviesRepository

class PopularMoviesListViewModel(repository:PopularMoviesRepository) : BaseViewModel<PopularMoviesRepository>(
    repository
) {

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


}