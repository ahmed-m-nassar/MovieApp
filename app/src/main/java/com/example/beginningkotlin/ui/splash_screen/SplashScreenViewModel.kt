package com.example.beginningkotlin.ui.splash_screen

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.example.beginningkotlin.ui.base.BaseViewModel
import com.example.beginningkotlin.data.repository.SplashScreenRepository

class SplashScreenViewModel(repository: SplashScreenRepository) : BaseViewModel<SplashScreenRepository>(
    repository
) {

    val isSplashing : MutableLiveData<Boolean> = MutableLiveData(true)

    fun startSplashing() {
        val handler : Handler = Handler()
        handler.postDelayed( {
            isSplashing.value = false
        } , 3000)
    }


}