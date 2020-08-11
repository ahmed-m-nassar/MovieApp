package com.example.beginningkotlin.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.beginningkotlin.R
import com.example.beginningkotlin.popular_movies.ui.MoviesListActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler : Handler = Handler()
        handler.postDelayed( {

            val intent = Intent(this , MoviesListActivity::class.java)
            startActivity(intent)
            finish()

        } , 3000)
    }
}
