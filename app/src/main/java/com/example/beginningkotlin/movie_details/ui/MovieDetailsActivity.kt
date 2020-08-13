package com.example.beginningkotlin.movie_details.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {

    lateinit var viewModel     : MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =  ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)


        val movieID : Int = intent.getIntExtra(NetworkConstants.MOVIE_ID_KEY , 1)
        viewModel!!.getMovieDetails(movieID)

        viewModel?.movieDetails?.observe(this, Observer { movieDetails ->
            Glide.with(this)
                .load(movieDetails.getFullImageURL())
                .centerCrop()
                .into(movie_details_movie_poster!!)

            movie_details_title?.setText(movieDetails.title)
            movie_details_rating?.setText(movieDetails.voteAverage.toString())
            movie_details_budget?.setText(movieDetails.budget.toString())
            movie_details_release_date?.setText(movieDetails.releaseDate)
        })

        viewModel?.isLoading?.observe(this, Observer { isLoading ->
            if(isLoading) {
                showView(movie_details_progress_bar)

            }
            else {
                hideView(movie_details_progress_bar)
            }
        })

        viewModel?.message?.observe(this, Observer { newMessage ->
            showToast(newMessage)
        })
    }

    override fun getLayoutResourseId(): Int {
        return R.layout.activity_movie_details
    }



}
