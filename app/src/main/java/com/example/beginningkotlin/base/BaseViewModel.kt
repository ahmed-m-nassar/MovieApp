package com.example.beginningkotlin.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.data.repository.base.BaseRepository

abstract class BaseViewModel<R : BaseRepository>() : ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val toastMessage : MutableLiveData<String> = MutableLiveData()
    var repository : R

    init {
        repository = getRepositoryType()
    }

    protected abstract fun getRepositoryType() : R

}