package com.incite.moviequiz.presentation.menu

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle

import androidx.core.content.res.ResourcesCompat
import androidx.core.content.ContextCompat
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import com.incite.moviequiz.*
import com.incite.moviequiz.domain.model.Data
import com.incite.moviequiz.presentation.arcade.ArcadeActivity
import com.incite.moviequiz.presentation.custom_view.FButton
import com.incite.moviequiz.presentation.guess.GuessActivity
import com.incite.moviequiz.presentation.shop.ShopActivity
import com.incite.moviequiz.presentation.truefalse.TrueFalseActivity
import com.incite.moviequiz.util.SoundManager

class MenuActivity : AppCompatActivity() {
    private lateinit var b1: FButton
    private lateinit var b2: FButton
    private lateinit var b3: FButton
    private lateinit var b4: FButton
    private lateinit var face: Typeface
    private lateinit var arcadeDrawable: Drawable
    private lateinit var guessDrawable: Drawable
    private lateinit var tfDrawable: Drawable
    private lateinit var shopDrawable: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        initSoundManager()

        initViews()

        setViews()

        loadPacks()

    }

    private fun initSoundManager(){
        if (!SoundManager.isLoaded) {
            SoundManager.init(applicationContext)
            SoundManager.isLoaded = true
        }
    }

    private fun loadPacks(){
        if (!Data.isLoaded) Data.loadPacks(
            applicationContext
        )
        Data.isLoaded = true
    }

    private fun initViews() {
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        face = ResourcesCompat.getFont(this, R.font.rounds)!!
        arcadeDrawable = ContextCompat.getDrawable(this, R.drawable.ic_cup)!!
        guessDrawable = ContextCompat.getDrawable(this, R.drawable.ic_hourglass)!!
        tfDrawable = ContextCompat.getDrawable(this, R.drawable.ic_medal)!!
        shopDrawable = ContextCompat.getDrawable(this, R.drawable.ic_shop)!!
    }

    private fun setViews() {
        arcadeDrawable.setBounds(0, 0, 100, 100)
        guessDrawable.setBounds(0, 0, 100, 100)
        tfDrawable.setBounds(0, 0, 100, 100)
        shopDrawable.setBounds(0, 0, 100, 100)

        b1.apply {
            typeface = face
            setFButtonPadding(30, 0, 30, 0)
            setCompoundDrawables(arcadeDrawable, null, null, null)
            b1.setOnClickListener { startArcadeActivity() }
        }

        b2.apply {
            typeface = face
            setFButtonPadding(30, 0, 30, 0)
            setCompoundDrawables(guessDrawable, null, null, null)
            b2.setOnClickListener { startGuessActivity() }

        }
        b3.apply {
            typeface = face
            setFButtonPadding(30, 0, 30, 0)
            setCompoundDrawables(tfDrawable, null, null, null)
            b3.setOnClickListener { startTrueFalseActivity() }
        }
        b4.apply {
            typeface = face
            setFButtonPadding(30, 0, 30, 0)
            setCompoundDrawables(shopDrawable, null, null, null)
            b4.setOnClickListener { startShopActivity() }
        }
    }

    private fun startArcadeActivity() {
        SoundManager.play(SoundManager.Click1)
        val i = Intent(this, ArcadeActivity::class.java)
        startActivity(i)
    }


    private fun startGuessActivity() {
        SoundManager.play(SoundManager.Click1)
        val i = Intent(this, GuessActivity::class.java)
        startActivity(i)
    }

    private fun startTrueFalseActivity() {
        SoundManager.play(SoundManager.Click1)
        val i = Intent(this, TrueFalseActivity::class.java)
        startActivity(i)
    }

    fun exitApplication() {
        SoundManager.play(SoundManager.Click1)
        System.exit(0)
    }

    private fun startShopActivity() {
        SoundManager.play(SoundManager.Click1)
        val i = Intent(this, ShopActivity::class.java)
        startActivity(i)
    }

}