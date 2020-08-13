package com.example.beginningkotlin.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beginningkotlin.R

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourseId())
    }

    protected abstract fun getLayoutResourseId() : Int

    fun showView(view : View) {
        view.visibility = View.VISIBLE
    }

    fun hideView(view : View) {
        view.visibility = View.GONE
    }

    fun showToast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}