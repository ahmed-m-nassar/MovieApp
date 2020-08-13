package com.example.beginningkotlin.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProviders
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseActivity
import com.example.beginningkotlin.popular_movies.ui.PopularMoviesListActivity


class SplashScreen : BaseActivity() {

    lateinit var viewModel     : SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)

        val handler : Handler = Handler()
        handler.postDelayed( {

            val intent = Intent(this , PopularMoviesListActivity::class.java)
            startActivity(intent)
            finish()

        } , 3000)
    }

    override fun getLayoutResourseId(): Int {
        return R.layout.activity_splash_screen
    }
}
