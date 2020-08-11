package com.example.beginningkotlin.movie_details.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.beginningkotlin.Constants
import com.example.beginningkotlin.R
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    var viewModel     : MovieDetailsViewModel? = null
    var movieDetailsWrapper   : LinearLayout? = null
    var progressBar   : ProgressBar? = null
    var moviePoster   : ImageView? = null
    var movieTitle    : TextView? = null
    var movieRating   : TextView? = null
    var movieBudget   : TextView? = null
    var movieDate     : TextView? = null
    var movieRevanues : TextView? = null

    var movieId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //initition wrong
        viewModel = MovieDetailsViewModel()
        //no need for findviewbyid
        movieDetailsWrapper = findViewById(R.id.move_details_wrapper)
        progressBar   = findViewById(R.id.movie_details_progress_bar)
        moviePoster   = findViewById(R.id.movie_details_movie_poster)
        movieTitle    =  findViewById(R.id.movie_details_title)
        movieRating   = findViewById(R.id.movie_details_rating)
        movieBudget   = findViewById(R.id.movie_details_budget)
        movieDate     =   findViewById(R.id.movie_details_release_date)
        movieRevanues = findViewById(R.id.movie_details_revenue)



        val movieID : Int = intent.getIntExtra(Constants.MOVIE_ID_KEY , 1)
        viewModel!!.getMovieDetails(movieID)

        viewModel?.movieDetails?.observe(this, Observer { movieDetails ->
            Glide.with(this)
                .load(Constants.POSTERS_BASE_URL + movieDetails.posterPath)
                .centerCrop()
                .into(moviePoster!!)
            movieTitle?.setText(movieDetails.title)
            movieRating?.setText(movieDetails.voteAverage.toString())
            movieBudget?.setText(movieDetails.budget.toString())
            movieDate?.setText(movieDetails.releaseDate)
        })

        viewModel?.isLoading?.observe(this, Observer { isLoading ->
            if(isLoading) {
                showLoadingScreen()

            }
            else {
                hideLoadingScreen()
            }
        })

        viewModel?.message?.observe(this, Observer { newMessage ->
            //extension
            val t = Toast.makeText(this, newMessage, Toast.LENGTH_LONG).show()
        })
    }

    fun showLoadingScreen() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideLoadingScreen() {
        progressBar?.visibility = View.GONE
    }

    fun showMovieDetails() {
        move_details_wrapper?.visibility = View.VISIBLE
    }

    fun hideMovieDetails() {
        move_details_wrapper?.visibility = View.GONE
    }
}
