package com.example.beginningkotlin.popular_movies.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseActivity
import com.example.beginningkotlin.movie_details.ui.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*

class PopularMoviesListActivity : BaseActivity(), PopularMoviesListAdapter.OnMovieListener {


    lateinit var viewModelPopular: PopularMoviesListViewModel
    var adapterPopular : PopularMoviesListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelPopular =
            ViewModelProviders.of(this).get(PopularMoviesListViewModel::class.java)

        adapterPopular =
            PopularMoviesListAdapter(
                this,
                listOf(),
                this
            )
        main_recycler_view?.layoutManager = LinearLayoutManager(this)
        main_recycler_view?.adapter = adapterPopular

        viewModelPopular?.getMovies()

        viewModelPopular?.movies?.observe(this, Observer { newMovies ->
            adapterPopular?.moviesList = newMovies
            adapterPopular?.notifyDataSetChanged()
        })

        viewModelPopular?.isLoading?.observe(this, Observer { isLoading ->
            if(isLoading) {
                showView(main_progress_bar)
            }
            else {
                hideView(main_progress_bar)
            }
        })

        viewModelPopular?.message?.observe(this, Observer { newMessage ->
            showToast(newMessage)
        })
    }

    override fun getLayoutResourseId(): Int {
        return R.layout.activity_main
    }



    override fun onMovieClick(position: Int) {
        val intent : Intent = Intent(this , MovieDetailsActivity::class.java)
        intent.putExtra(NetworkConstants.MOVIE_ID_KEY , adapterPopular!!.moviesList[position].id)
        this.startActivity(intent)
    }

}
