package com.example.beginningkotlin.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.beginningkotlin.R
import com.example.beginningkotlin.dialog.BaseDialog

abstract class BaseDialogFragment<T : BaseViewModel<*>> : DialogFragment() {
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
        val loadingDialog =
            BaseDialog(this.requireContext(),R.layout.custom_loading_dialog)
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                Log.d("BaseDialogFragment", "I am loading")
                loadingDialog.startAnimation()

            } else {
                Log.d("BaseDialogFragment", "Stopped loading")
                loadingDialog.dismissAnimation()
            }
        })
        viewModel?.toastMessage?.observe(viewLifecycleOwner, Observer {
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