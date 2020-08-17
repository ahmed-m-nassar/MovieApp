package com.example.beginningkotlin.base

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.beginningkotlin.R
import com.example.beginningkotlin.movie_details.ui.MovieDetailsViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import com.example.beginningkotlin.base.BaseViewModel

abstract class BaseActivity<T : BaseViewModel<*>> : AppCompatActivity() {
    lateinit var viewModel: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourseId())


        viewModel = ViewModelProviders.of(this).get(getViewModelType()::class.java)


        val loadingDialog = LoadingDialog(this)
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                Log.d("BaseActivity" , "I am loading")
                loadingDialog.startLoadingAnimation()

            } else {
                Log.d("BaseActivity" , "Stopped loading")
                loadingDialog.dismissLoadingAnimation()
            }
        })

        viewModel?.toastMessage?.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.repository.errorMessage.observe(this , Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })


    }

    protected abstract fun getLayoutResourseId(): Int
    protected abstract fun getViewModelType(): T

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}