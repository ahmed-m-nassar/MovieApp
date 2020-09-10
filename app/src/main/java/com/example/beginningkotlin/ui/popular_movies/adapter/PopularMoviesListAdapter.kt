package com.example.beginningkotlin.ui.popular_movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.beginningkotlin.R
import com.example.beginningkotlin.databinding.MovieItemBinding
import com.example.beginningkotlin.ui.popular_movies.model.PopularMoviesUIModel


class PopularMoviesListAdapter(val context : Context, var moviesList : List<PopularMoviesUIModel>,
                               val onMovieListener : OnMovieListener
) : RecyclerView.Adapter<PopularMoviesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false) , onMovieListener
        )

    override fun getItemCount(): Int {
       return  moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.recyclerViewBinding.item = moviesList[position]
        holder.recyclerViewBinding.item = moviesList[position]
    }


    inner class ViewHolder( val recyclerViewBinding : MovieItemBinding,
                            val onMovieListener : OnMovieListener
    )
                            : RecyclerView.ViewHolder(recyclerViewBinding.root), View.OnClickListener {
        val parent =itemView.findViewById(R.id.movie_list_item_parent) as LinearLayout
        init {
            parent.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onMovieListener.onMovieClick(adapterPosition)
        }
    }
    interface OnMovieListener{
        fun onMovieClick(position : Int)
    }


}