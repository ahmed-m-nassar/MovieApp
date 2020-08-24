package com.example.beginningkotlin.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.beginningkotlin.R
import com.example.beginningkotlin.base.BaseFragment
import com.example.beginningkotlin.popular_movies.ui.PopularMoviesListActivity

/**
 * A simple [Fragment] subclass.
 */
class SplashScreenFragment : BaseFragment<SplashScreenViewModel>() {

    var navController : NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.startSplashing()
        viewModel.isSplashing.observe(viewLifecycleOwner, Observer { isSplashing ->
            if(!isSplashing) {
                navController!!.navigate(R.id.action_splashScreenFragment_to_popularMoviesFragment)
            }
        })
    }
    override fun getLayoutResourseId(): Int {
        return R.layout.activity_splash_screen
    }

    override fun getViewModelType(): SplashScreenViewModel {
        return SplashScreenViewModel()
    }

}
