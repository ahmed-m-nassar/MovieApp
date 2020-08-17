package com.example.beginningkotlin.splash_screen

import android.content.Intent
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.base.BaseViewModel
import com.example.beginningkotlin.popular_movies.ui.PopularMoviesListActivity
import com.example.beginningkotlin.splash_screen.data.repository.SplashScreenRepository

class SplashScreenViewModel : BaseViewModel<SplashScreenRepository>() {

    val isSplashing : MutableLiveData<Boolean> = MutableLiveData(true)

    fun startSplashing() {
        val handler : Handler = Handler()
        handler.postDelayed( {
            isSplashing.value = false
        } , 3000)
    }

    override fun getRepositoryType(): SplashScreenRepository {
        return SplashScreenRepository()
    }

}