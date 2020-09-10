package com.example.beginningkotlin.data.repository.di

import com.example.beginningkotlin.data.repository.ChatRepository
import com.example.beginningkotlin.data.repository.MainScreenRepository
import com.example.beginningkotlin.data.repository.PopularMoviesRepository
import com.example.beginningkotlin.data.repository.SplashScreenRepository
import com.example.beginningkotlin.ui.popular_movies.data.repository.MovieDetailsRepository
import org.koin.core.module.Module
import org.koin.dsl.module


val repoModule: Module = module {
    factory { PopularMoviesRepository() }
    factory { MainScreenRepository() }
    factory { MovieDetailsRepository() }
    factory { SplashScreenRepository() }
    factory { ChatRepository() }
}