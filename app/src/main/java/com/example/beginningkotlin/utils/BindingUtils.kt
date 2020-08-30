package com.example.beginningkotlin.utils

import android.widget.Adapter
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("imageURL")
public fun setImageResource(imageView : ImageView, imageurl : String?) {
    if (!imageurl.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(imageurl)
            .centerCrop()
            .into(imageView)
    }
}

@BindingAdapter("adapter")
public fun setAdapter(recyclerView: RecyclerView , adapter : RecyclerView.Adapter<*>) {
    recyclerView.adapter = adapter
}