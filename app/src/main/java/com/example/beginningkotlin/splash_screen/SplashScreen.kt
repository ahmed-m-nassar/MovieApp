package com.example.beginningkotlin.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseActivity
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.movie_details.ui.MovieDetailsActivity
import com.example.beginningkotlin.popular_movies.ui.PopularMoviesListActivity


class SplashScreen : BaseActivity<SplashScreenViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startSplashing()
        viewModel.isSplashing.observe(this, Observer { isSplashing ->
            if(!isSplashing) {
                val intent : Intent = Intent(this , PopularMoviesListActivity::class.java)
                this.startActivity(intent)
                finish()
            }
        })
    }

    override fun getLayoutResourseId(): Int {
        return R.layout.activity_splash_screen
    }

    override fun getViewModelType(): SplashScreenViewModel {
        return SplashScreenViewModel()
    }
    //Todo
    //Splash handler in view model'
    //Dialog
    //message to be removed -> base
    //change layut name of activity_popular_movies
}
