package com.example.beginningkotlin.data.repository.base

import androidx.lifecycle.MutableLiveData

open class BaseRepository {
    val errorMessage: MutableLiveData<String> = MutableLiveData()
}