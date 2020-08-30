package com.example.beginningkotlin.splash_screen

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.example.beginningkotlin.base.BaseViewModel
import com.example.beginningkotlin.data.repository.SplashScreenRepository

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