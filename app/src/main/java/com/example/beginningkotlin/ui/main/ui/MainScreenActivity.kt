package com.example.beginningkotlin.ui.main.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.beginningkotlin.R
import com.example.beginningkotlin.ui.base.BaseActivity
import com.example.beginningkotlin.databinding.ActivityMainScreenBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainScreenActivity : BaseActivity<MainScreenViewModel>() {
    var binding: ActivityMainScreenBinding? = null
    private val mainScreenViewModel:MainScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen)
        binding?.viewModel = viewModel
    }

    override fun getLayoutResourseId(): Int {
        return R.layout.activity_main_screen
    }

    override fun getViewModelType(): MainScreenViewModel {
       return mainScreenViewModel
    }
}
