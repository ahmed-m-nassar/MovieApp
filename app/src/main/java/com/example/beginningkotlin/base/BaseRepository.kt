package com.example.beginningkotlin.base

import androidx.lifecycle.MutableLiveData

open class BaseRepository {
    val errorMessage: MutableLiveData<String> = MutableLiveData()
}