package com.example.beginningkotlin.popular_movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseFragment
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.databinding.FragmentPopularMoviesBinding
import com.example.beginningkotlin.popular_movies.adapter.PopularMoviesListAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*

/**
 * A simple [Fragment] subclass.
 */
class PopularMoviesFragment : BaseFragment<PopularMoviesListViewModel>()
    , PopularMoviesListAdapter.OnMovieListener {
    var adapterPopular: PopularMoviesListAdapter? = null
    var navController: NavController? = null
    var binding: FragmentPopularMoviesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_popular_movies,
            container, false
        )
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding?.viewModel = viewModel
        adapterPopular =
            this.context?.let {
                PopularMoviesListAdapter(
                    it,
                    listOf(),
                    this
                )
            }
        binding?.adapter = adapterPopular
        viewModel.getMovies()
        viewModel?.popularMovies?.observe(viewLifecycleOwner, Observer { movies ->
            adapterPopular?.moviesList = movies
            adapterPopular?.notifyDataSetChanged()
        })
    }


    override fun onMovieClick(position: Int) {
        val bundle =
            bundleOf(NetworkConstants.MOVIE_ID_KEY to adapterPopular!!.moviesList[position].movieID)
        navController!!.navigate(R.id.action_popularMoviesFragment_to_movieDetailsFragment, bundle)
    }

    override fun getViewModelType(): PopularMoviesListViewModel {
        return PopularMoviesListViewModel()
    }

}
