package com.example.beginningkotlin.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.beginningkotlin.R

abstract class BaseFragment<T : BaseViewModel<*>> : Fragment() {
    lateinit var viewModel: T
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(getViewModelType()::class.java)


        val loadingDialog = LoadingDialogFragment(activity)
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                Log.d("BaseFragment", "I am loading")
                loadingDialog.startLoadingAnimation()

            } else {
                Log.d("BaseFragment", "Stopped loading")
                loadingDialog.dismissLoadingAnimation()
            }
        })

        viewModel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })

        viewModel.repository.errorMessage.observe(this.viewLifecycleOwner, Observer {
            showToast(it)
            loadingDialog.dismissLoadingAnimation()
        })


    }

    protected abstract fun getLayoutResourseId(): Int
    protected abstract fun getViewModelType(): T

    fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }
}