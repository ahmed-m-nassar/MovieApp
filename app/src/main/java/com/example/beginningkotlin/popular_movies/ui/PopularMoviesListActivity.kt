package com.example.beginningkotlin.popular_movies.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseActivity
import com.example.beginningkotlin.movie_details.ui.MovieDetailsActivity
import com.example.beginningkotlin.popular_movies.data.repository.MovieDetailsRepository
import com.example.beginningkotlin.popular_movies.data.repository.PopularMoviesRepository
import kotlinx.android.synthetic.main.activity_popular_movies.*

class PopularMoviesListActivity : BaseActivity<PopularMoviesListViewModel>()
    , PopularMoviesListAdapter.OnMovieListener {


    var adapterPopular : PopularMoviesListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapterPopular =
            PopularMoviesListAdapter(
                this,
                listOf(),
                this
            )
        popular_movies_recycler_view?.layoutManager = LinearLayoutManager(this)
        popular_movies_recycler_view?.adapter = adapterPopular

        viewModel.getMovies()
        viewModel?.popularMovies?.observe(this , Observer {
            Log.d("PopularMoviesActivity" , "observed")
            adapterPopular?.moviesList = it
            adapterPopular?.notifyDataSetChanged()
        })
    }

    override fun getLayoutResourseId(): Int {
        return R.layout.activity_popular_movies
    }



    override fun onMovieClick(position: Int) {
        val intent : Intent = Intent(this , MovieDetailsActivity::class.java)
        intent.putExtra(NetworkConstants.MOVIE_ID_KEY , adapterPopular!!.moviesList[position].movieID)
        this.startActivity(intent)
    }

    override fun getViewModelType(): PopularMoviesListViewModel {
        return PopularMoviesListViewModel()
    }

}
