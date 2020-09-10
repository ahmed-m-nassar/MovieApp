package com.example.beginningkotlin.ui.movie_details.di

import com.example.beginningkotlin.ui.movie_details.ui.MovieDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val movieDetailsModule: Module = module {
    viewModel { MovieDetailsViewModel(get()) }
}