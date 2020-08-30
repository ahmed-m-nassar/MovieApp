package com.example.beginningkotlin.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseViewModel
import com.example.beginningkotlin.data.repository.MainScreenRepository

class MainScreenViewModel :BaseViewModel<MainScreenRepository>(){
    override fun getRepositoryType(): MainScreenRepository {
        return MainScreenRepository()
    }
}
