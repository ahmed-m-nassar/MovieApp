package com.example.beginningkotlin.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseActivity
import com.example.beginningkotlin.databinding.ActivityMainScreenBinding
import com.example.beginningkotlin.databinding.FragmentPopularMoviesBinding

class MainScreenActivity : BaseActivity<MainScreenViewModel>() {
    var binding: ActivityMainScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen)
        binding?.viewModel = viewModel
    }

    override fun getLayoutResourseId(): Int {
        return R.layout.activity_main_screen
    }

    override fun getViewModelType(): MainScreenViewModel {
        return MainScreenViewModel()
    }
}
