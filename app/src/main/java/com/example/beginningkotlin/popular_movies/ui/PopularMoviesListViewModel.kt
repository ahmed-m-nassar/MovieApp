package com.example.beginningkotlin.popular_movies.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.beginningkotlin.base.BaseViewModel
import com.example.beginningkotlin.popular_movies.data.model.MovieModel
import com.example.beginningkotlin.popular_movies.data.model.ui_model.PopularMoviesUIModel
import com.example.beginningkotlin.popular_movies.data.repository.MovieDetailsRepository
import com.example.beginningkotlin.popular_movies.data.repository.PopularMoviesRepository

class PopularMoviesListViewModel : BaseViewModel<PopularMoviesRepository>() {

    var popularMovies : LiveData<ArrayList<PopularMoviesUIModel>>? = null
    fun getMovies() {
        isLoading.value = true
        val result = repository.getPopularMovies()
        popularMovies = Transformations.switchMap(result) {
            val transformedPopularMovies :ArrayList<PopularMoviesUIModel>  = ArrayList<PopularMoviesUIModel>()

            it.forEach { item ->
                transformedPopularMovies.add(PopularMoviesUIModel(item.getFullImageURL(),
                    item.originalTitle,
                    item.releaseDate,
                    item.id.toString()))
                Log.d("PopularMoviesViewModel" , "transformed")

            }
            isLoading.value = false
            MutableLiveData(transformedPopularMovies)
        }
    }

    override fun getRepositoryType(): PopularMoviesRepository {
        return PopularMoviesRepository()
    }
}