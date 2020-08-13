package com.example.beginningkotlin.base

import com.example.beginningkotlin.constants.NetworkConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
}