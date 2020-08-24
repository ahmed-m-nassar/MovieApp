package com.example.beginningkotlin.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<T : BaseViewModel<*>> : AppCompatActivity() {
    lateinit var viewModel: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourseId())


        viewModel = ViewModelProviders.of(this).get(getViewModelType()::class.java)


        val loadingDialog = LoadingDialogActivity(this)
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                Log.d("BaseActivity", "I am loading")
                loadingDialog.startLoadingAnimation()

            } else {
                Log.d("BaseActivity", "Stopped loading")
                loadingDialog.dismissLoadingAnimation()
            }
        })

        viewModel?.toastMessage?.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.repository.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            loadingDialog.dismissLoadingAnimation()
        })


    }

    protected abstract fun getLayoutResourseId(): Int
    protected abstract fun getViewModelType(): T

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}