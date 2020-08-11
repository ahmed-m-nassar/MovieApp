package com.example.beginningkotlin.popular_movies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beginningkotlin.Constants
import com.example.beginningkotlin.R
import com.example.beginningkotlin.movie_details.ui.MovieDetailsActivity

class MoviesListActivity : AppCompatActivity() , MoviesListAdapter.OnMovieListener {

    var viewModel: MoviesListViewModel? = null
    var progressBar : ProgressBar? = null
    var recyclerView : RecyclerView? = null
    var adapter : MoviesListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =
            MoviesListViewModel()
        progressBar = findViewById(R.id.main_progress_bar)
        recyclerView = findViewById(R.id.main_recycler_view)
        adapter =
            MoviesListAdapter(
                this,
                listOf(),
                this
            )
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter

        viewModel?.getMovies()


        viewModel?.movies?.observe(this, Observer { newMovies ->
            showMoviesList()
            hideLoadingScreen()
            adapter?.moviesList = newMovies
            adapter?.notifyDataSetChanged()
        })

        viewModel?.isLoading?.observe(this, Observer { isLoading ->
            if(isLoading) {
                showLoadingScreen()
                hideMoviesList()
            }
            else {
                hideLoadingScreen()
            }
        })

        viewModel?.message?.observe(this, Observer { newMessage ->
            val t = Toast.makeText(this, newMessage, Toast.LENGTH_LONG).show()
        })
    }

    fun showLoadingScreen() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideLoadingScreen() {
        progressBar?.visibility = View.GONE
    }

    fun showMoviesList() {
        recyclerView?.visibility = View.VISIBLE
    }

    fun hideMoviesList() {
        recyclerView?.visibility = View.GONE
    }

    override fun onMovieClick(position: Int) {
        val intent : Intent = Intent(this , MovieDetailsActivity::class.java)
        intent.putExtra(Constants.MOVIE_ID_KEY , adapter!!.moviesList[position].id)
        this.startActivity(intent)
    }

}
