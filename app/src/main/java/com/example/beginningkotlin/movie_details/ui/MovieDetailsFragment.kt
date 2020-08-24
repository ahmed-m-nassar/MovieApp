package com.example.beginningkotlin.movie_details.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseFragment
import com.example.beginningkotlin.constants.NetworkConstants
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsFragment :  BaseFragment<MovieDetailsViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieID: String? = requireArguments().getString(NetworkConstants.MOVIE_ID_KEY)
        viewModel.getMovieDetails(movieID!!.toInt())
        viewModel.movieDetails?.observe(viewLifecycleOwner, Observer { movieDetails ->
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
