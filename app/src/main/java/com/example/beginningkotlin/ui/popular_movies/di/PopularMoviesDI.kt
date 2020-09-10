package com.example.beginningkotlin.ui.popular_movies.di

import com.example.beginningkotlin.ui.popular_movies.ui.PopularMoviesListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val popularMoviesModule: Module = module {
    viewModel { PopularMoviesListViewModel(get()) }
}