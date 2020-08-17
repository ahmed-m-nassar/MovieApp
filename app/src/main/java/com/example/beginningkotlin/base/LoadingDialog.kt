package com.example.beginningkotlin.base

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import com.example.beginningkotlin.R

class LoadingDialog(val activity : Activity ) {
    private var alertDialog : AlertDialog? = null

    fun startLoadingAnimation() {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater : LayoutInflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_loading_dialog , null))
        builder.setCancelable(true)

        alertDialog = builder.create()
        alertDialog?.show()
    }

    fun dismissLoadingAnimation() {
        alertDialog?.dismiss()
    }
}