package com.example.beginningkotlin.ui.splash_screen.di

import com.example.beginningkotlin.ui.splash_screen.SplashScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val splashScreenModule: Module = module {
    viewModel { SplashScreenViewModel(get()) }
}