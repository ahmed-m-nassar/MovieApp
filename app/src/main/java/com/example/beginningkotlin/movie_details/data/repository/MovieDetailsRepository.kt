package com.example.beginningkotlin.popular_movies.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.beginningkotlin.base.BaseRepository
import com.example.beginningkotlin.base.Resource
import com.example.beginningkotlin.base.RetrofitBuilder
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.movie_details.data.api.MovieDetailsApi
import com.example.beginningkotlin.movie_details.data.model.MovieDetailsModel
import com.example.beginningkotlin.popular_movies.data.api.PopularMoviesApi
import com.example.beginningkotlin.popular_movies.data.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailsRepository : BaseRepository() {
    val api = RetrofitBuilder.retrofitBuilder.build().create(MovieDetailsApi::class.java)

     fun getMovieDetails (movieID : Int) : LiveData<MovieDetailsModel> {
         val responseMutableLiveData : MutableLiveData<MovieDetailsModel> = MutableLiveData()
         GlobalScope.launch(Dispatchers.Main) {
             lateinit var resource : Resource<LiveData<MovieDetailsModel>>
             try {
                 val response = api.getMovieDetails(movieID , NetworkConstants.API_KEY_VALUE)
                 if(response.isSuccessful) {
                     val movieDetails : LiveData<MovieDetailsModel> = MutableLiveData(response.body())
                     resource = Resource(Resource.Status.SUCCESS,movieDetails,null)
                 } else {
                     resource = Resource(Resource.Status.ERROR,null
                         ,response.message() + " Error code = " + response.code())
                     Log.d("urllll",response.raw().request().url().toString())
                 }

             } catch(exception : Exception) {
                 resource =  Resource(Resource.Status.ERROR,null
                     ,exception.message)
             }

             if (resource.status == Resource.Status.SUCCESS){
                 responseMutableLiveData.value = resource.data?.value
             }else{
                 // livedata of error message you will observe  on it on base activity and view message in toast
                 errorMessage.value = resource.message
             }
         }
         return responseMutableLiveData

    }
}