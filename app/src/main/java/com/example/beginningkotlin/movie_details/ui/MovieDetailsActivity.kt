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

        val movieID: Int = intent.getIntExtra(NetworkConstants.MOVIE_ID_KEY, 1)

        viewModel.getMovieDetails(movieID)?.observe(this, Observer { movieDetails ->
            Glide.with(this)
                .load(movieDetails.getFullImageURL())
                .centerCrop()
                .into(movie_details_movie_poster!!)

            movie_details_title?.setText(movieDetails.title)
            movie_details_rating?.setText(movieDetails.voteAverage.toString())
            movie_details_budget?.setText(movieDetails.budget.toString())
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
