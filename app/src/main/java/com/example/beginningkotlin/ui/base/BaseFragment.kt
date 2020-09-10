package com.example.beginningkotlin.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.beginningkotlin.R
import com.example.beginningkotlin.dialog.BaseDialog

abstract class BaseFragment<T : BaseViewModel<*>> : Fragment() {
    lateinit var viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(getViewModelType()::class.java)
        val loadingDialog =
            BaseDialog(this!!.context!!, R.layout.custom_loading_dialog)
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                Log.d("BaseFragment", "I am loading")
                loadingDialog.startAnimation()

            } else {
                Log.d("BaseFragment", "Stopped loading")
                loadingDialog.dismissAnimation()
            }
        })

        viewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })

        viewModel.repository.errorMessage.observe(this.viewLifecycleOwner, Observer {
            showToast(it)
            loadingDialog.dismissAnimation()
        })


    }

    protected abstract fun getViewModelType(): T

    fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }
}