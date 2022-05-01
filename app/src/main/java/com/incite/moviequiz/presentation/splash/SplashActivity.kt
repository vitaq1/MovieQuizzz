package com.incite.moviequiz.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.incite.moviequiz.presentation.menu.MenuActivity
import com.incite.moviequiz.R

class SplashActivity : AppCompatActivity() {
    private lateinit var splashAnimation: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashAnimation = findViewById(R.id.splash_anim)
        splashAnimation.speed = 1.2f
        splashAnimation.playAnimation()

        Handler().postDelayed({
            val i = Intent(this, MenuActivity::class.java)
            startActivity(i)
            finish()
        }, 300) //2600 def
    }
}