package com.example.beginningkotlin.movie_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseFragment
import com.example.beginningkotlin.constants.NetworkConstants
import com.example.beginningkotlin.databinding.RateDialogBinding
import com.example.beginningkotlin.databinding.FragmentMovieDetailsBinding
import com.example.beginningkotlin.dialog.BaseDialog


class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {
    var binding: FragmentMovieDetailsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_details,
            container, false
        )
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        val movieID: String? = requireArguments().getString(NetworkConstants.MOVIE_ID_KEY)
        viewModel.getMovieDetails(movieID!!.toInt())
        viewModel.movieDetails?.observe(viewLifecycleOwner, Observer { movieDetails ->
            binding?.movieDetails = movieDetails
        })

        binding?.movieDetailsRateButton?.setOnClickListener {
            val rateDialog : BaseDialog = BaseDialog(requireContext() , R.layout.rate_dialog)
            val view = rateDialog.startAnimation()
            var rateBinding: RateDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.rate_dialog,
            null, false
            )
            rateBinding.viewModel = viewModel
            rateBinding.ratingDialogSubmitButton.setOnClickListener {
                viewModel.rateMovie(movieID.toInt())
            }
        }
    }

    override fun getViewModelType(): MovieDetailsViewModel {
        return MovieDetailsViewModel()
    }


}
