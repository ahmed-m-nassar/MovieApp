package com.example.beginningkotlin.ui.main.di

import com.example.beginningkotlin.ui.main.ui.MainScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainModule: Module = module {
    viewModel { MainScreenViewModel(get()) }
}