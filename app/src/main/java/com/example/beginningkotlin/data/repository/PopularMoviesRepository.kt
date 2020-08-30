package com.example.beginningkotlin.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.beginningkotlin.data.repository.base.BaseRepository
import com.example.beginningkotlin.network.Resource
import com.example.beginningkotlin.network.RetrofitBuilder
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.data.api.MovieAPI
import com.example.beginningkotlin.data.response_model.MovieResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class PopularMoviesRepository : BaseRepository() {
     val api = RetrofitBuilder.retrofitBuilder.build().create(MovieAPI::class.java)

     fun getPopularMovies () : LiveData<List<MovieResponseModel>> {
         val responseMutableLiveData : MutableLiveData<List<MovieResponseModel>> = MutableLiveData()
         GlobalScope.launch(Dispatchers.Main) {
             lateinit var resource : Resource<LiveData<List<MovieResponseModel>>>
             try {
                 val response = api.getMovies(NetworkConstants.API_KEY_VALUE)
                 if(response.isSuccessful) {
                     val movies : LiveData<List<MovieResponseModel>> = MutableLiveData(response.body()?.results)
                     resource = Resource(
                         Resource.Status.SUCCESS,
                         movies,
                         null
                     )
                 } else {
                     Resource(
                         Resource.Status.ERROR,
                         null
                         ,
                         response.message() + " Error code = " + response.code()
                     )
                 }

             } catch(exception : Exception) {
                 resource = Resource(
                     Resource.Status.ERROR,
                     null
                     ,
                     exception.message
                 )
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