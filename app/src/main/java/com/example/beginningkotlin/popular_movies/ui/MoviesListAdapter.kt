package com.example.beginningkotlin.popular_movies.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beginningkotlin.Constants
import com.example.beginningkotlin.R
import com.example.beginningkotlin.popular_movies.data.model.MovieModel

class MoviesListAdapter(val context : Context, var moviesList : List<MovieModel> ,  val onMovieListener : OnMovieListener) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.movie , parent , false)
        return ViewHolder(
            v , onMovieListener
        )
    }

    override fun getItemCount(): Int {
       return  moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.title?.text = moviesList[position].originalTitle
        holder?.releaseDate?.text = moviesList[position].releaseDate

        val posterURL : String = Constants.POSTERS_BASE_URL + moviesList[position].posterPath
        val url = if (posterURL != null) "$posterURL?w=360" else null
        Glide.with(context)
            .load(url)
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