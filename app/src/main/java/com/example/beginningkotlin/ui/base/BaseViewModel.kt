package com.example.beginningkotlin.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beginningkotlin.data.repository.base.BaseRepository

abstract class BaseViewModel<R : BaseRepository>(val repository : R) : ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val toastMessage : MutableLiveData<String> = MutableLiveData()
}