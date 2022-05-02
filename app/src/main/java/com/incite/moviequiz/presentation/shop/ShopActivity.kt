package com.incite.moviequiz.presentation.shop

import com.airbnb.lottie.LottieAnimationView
import android.graphics.Typeface
import android.widget.TextView
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.incite.moviequiz.R
import androidx.core.content.res.ResourcesCompat
import com.incite.moviequiz.domain.model.DataLoader
import com.incite.moviequiz.domain.model.Player
import com.incite.moviequiz.util.SoundManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShopActivity : AppCompatActivity() {

    private lateinit var sound: LottieAnimationView
    private lateinit var face: Typeface
    private lateinit var money: TextView

    @Inject
    lateinit var dataLoader: DataLoader
    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        player = dataLoader.repo.getPlayer()
        initViews()
        setViews()

    }

    private fun initViews(){
        sound = findViewById(R.id.soundOnOff6)
        face = ResourcesCompat.getFont(this, R.font.rounds)!!
        money = findViewById(R.id.moneyTv6)
    }

    private fun setViews(){
        sound.setMinFrame(60)
        if(!SoundManager.isOn) sound.frame = 60
        else sound.frame = 90

        money.text = player?.money.toString()
    }

    fun checkSound(view: View?) {
         if (SoundManager.isOn) {
            SoundManager.play(SoundManager.Bubble);
            SoundManager.isOn = false;
            SoundManager.stop(SoundManager.Tick);
            sound.setSpeed(-1.5f);
        } else {
            SoundManager.isOn = true;
            SoundManager.play(SoundManager.Bubble);
            sound.setSpeed(1.5f);
        }
        sound.playAnimation()
    }
}