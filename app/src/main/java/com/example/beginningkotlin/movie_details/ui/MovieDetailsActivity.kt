package com.example.beginningkotlin.movie_details.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseActivity
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.popular_movies.data.repository.MovieDetailsRepository
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity<MovieDetailsViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieID: String = intent.getStringExtra(NetworkConstants.MOVIE_ID_KEY)
        viewModel.getMovieDetails(movieID.toInt())
        viewModel.movieDetails?.observe(this, Observer { movieDetails ->
            Glide.with(this)
                .load(movieDetails.posterURL)
                .centerCrop()
                .into(movie_details_movie_poster!!)

            movie_details_title?.setText(movieDetails.movieTitle)
            movie_details_rating?.setText(movieDetails.rate)
            movie_details_budget?.setText(movieDetails.budget)
            movie_details_release_date?.setText(movieDetails.releaseDate)
        })

    }

    override fun getLayoutResourseId(): Int {
        return R.layout.activity_movie_details
    }

    override fun getViewModelType(): MovieDetailsViewModel {
        return MovieDetailsViewModel()
    }


}
