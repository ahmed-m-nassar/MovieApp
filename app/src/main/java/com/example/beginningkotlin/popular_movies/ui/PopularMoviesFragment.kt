package com.example.beginningkotlin.popular_movies.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseActivity
import com.example.beginningkotlin.base.BaseFragment
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.movie_details.ui.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_popular_movies.*

/**
 * A simple [Fragment] subclass.
 */
class PopularMoviesFragment : BaseFragment<PopularMoviesListViewModel>()
    , PopularMoviesListAdapter.OnMovieListener {
    var adapterPopular : PopularMoviesListAdapter? = null
    var navController : NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        adapterPopular =
            this.context?.let {
                PopularMoviesListAdapter(
                    it,
                    listOf(),
                    this
                )
            }
        popular_movies_recycler_view?.layoutManager = LinearLayoutManager(this.context)
        popular_movies_recycler_view?.adapter = adapterPopular

        viewModel.getMovies()
        viewModel?.popularMovies?.observe(viewLifecycleOwner , Observer {
            Log.d("PopularMoviesActivity" , "observed")
            adapterPopular?.moviesList = it
            adapterPopular?.notifyDataSetChanged()
        })
    }


    override fun getLayoutResourseId(): Int {
        return R.layout.activity_popular_movies
    }



    override fun onMovieClick(position: Int) {
//        val intent : Intent = Intent(this , MovieDetailsActivity::class.java)
//        intent.putExtra(NetworkConstants.MOVIE_ID_KEY , adapterPopular!!.moviesList[position].movieID)
//        this.startActivity(intent)
        val bundle = bundleOf(NetworkConstants.MOVIE_ID_KEY to adapterPopular!!.moviesList[position].movieID)
        navController!!.navigate(R.id.action_popularMoviesFragment_to_movieDetailsFragment , bundle)
    }

    override fun getViewModelType(): PopularMoviesListViewModel {
        return PopularMoviesListViewModel()
    }

}
