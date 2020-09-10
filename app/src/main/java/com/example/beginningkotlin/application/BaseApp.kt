package com.example.beginningkotlin.application

import android.app.Application
import com.example.beginningkotlin.data.repository.di.repoModule
import com.example.beginningkotlin.ui.chat.di.chatModule
import com.example.beginningkotlin.ui.main.di.mainModule
import com.example.beginningkotlin.ui.movie_details.di.movieDetailsModule
import com.example.beginningkotlin.ui.popular_movies.di.popularMoviesModule
import com.example.beginningkotlin.ui.splash_screen.di.splashScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            modules(listOf(repoModule , mainModule , splashScreenModule , popularMoviesModule,
                movieDetailsModule , chatModule  ))
        }
    }

}