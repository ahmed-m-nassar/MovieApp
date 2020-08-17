package com.example.beginningkotlin.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<R : BaseRepository> : ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val toastMessage : MutableLiveData<String> = MutableLiveData()
    var repository : R

    init {
        repository = getRepositoryType()
    }

    protected abstract fun getRepositoryType() : R

}