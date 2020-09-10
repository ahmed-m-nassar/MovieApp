package com.example.beginningkotlin.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View

class BaseDialog(val context: Context, val layoutID: Int) {
    private var alertDialog : AlertDialog? = null
    fun startAnimation(): View? {
        val builder : AlertDialog.Builder = AlertDialog.Builder(context)
        val inflater : LayoutInflater = LayoutInflater.from(context)
        var inflate = inflater.inflate(layoutID, null)
        builder.setView(inflate)
        builder.setCancelable(true)
        alertDialog = builder.create()
        alertDialog?.show()
        return inflate
    }

    fun dismissAnimation() {
        alertDialog?.dismiss()
    }
}