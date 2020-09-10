package com.example.beginningkotlin.ui.splash_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.beginningkotlin.R
import com.example.beginningkotlin.ui.base.BaseFragment
import com.example.beginningkotlin.databinding.FragmentSplashScreenBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SplashScreenFragment : BaseFragment<SplashScreenViewModel>() {

    var navController: NavController? = null
    var binding: FragmentSplashScreenBinding? = null
    private val splashScreenViewModel:SplashScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_splash_screen,
            container, false
        )
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding?.viewModel = viewModel
        viewModel.startSplashing()
        viewModel.isSplashing.observe(viewLifecycleOwner, Observer { isSplashing ->
            if (!isSplashing) {
                navController!!.navigate(R.id.action_splashScreenFragment_to_popularMoviesFragment)
            }
        })
    }

    override fun getViewModelType(): SplashScreenViewModel {
        return splashScreenViewModel
    }
}
