package com.example.beginningkotlin.popular_movies.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beginningkotlin.R
import com.example.beginningkotlin.popular_movies.data.model.MovieModel
import com.example.beginningkotlin.popular_movies.data.model.ui_model.PopularMoviesUIModel

class PopularMoviesListAdapter(val context : Context, var moviesList : List<PopularMoviesUIModel>,
                               val onMovieListener : OnMovieListener) : RecyclerView.Adapter<PopularMoviesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item , parent , false)
        return ViewHolder(
            v , onMovieListener
        )
    }

    override fun getItemCount(): Int {
       return  moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.title?.text = moviesList[position].movieTitle
        holder?.releaseDate?.text = moviesList[position].releaseDate

        val posterURL : String = moviesList[position].posterURL
        Glide.with(context)
            .load(posterURL)
            .centerCrop()
            .into(holder?.poster)


    }


    inner class ViewHolder( itemView : View , val onMovieListener : OnMovieListener) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val poster = itemView.findViewById(R.id.movie_list_item_poster_image_view) as ImageView
        val title = itemView.findViewById(R.id.movie_list_item_title_text_view) as TextView
        val releaseDate = itemView.findViewById(R.id.movie_list_item_release_date_text_view) as TextView
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