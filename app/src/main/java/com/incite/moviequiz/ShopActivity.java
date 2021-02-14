package com.incite.moviequiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.airbnb.lottie.LottieAnimationView;


public class ShopActivity extends AppCompatActivity {

    LottieAnimationView sound;

    Typeface face;
    TextView  money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        sound = findViewById(R.id.soundOnOff6);
        sound.setMinFrame(60);
        if(!SoundManager.isOn) sound.setFrame(60);
        else sound.setFrame(90);
        face = ResourcesCompat.getFont(this, R.font.rounds);
        money = findViewById(R.id.moneyTv6);

        money.setText(String.valueOf(Player.money));



    }

    public void checkSound(View view) {
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
        sound.playAnimation();
    }
}
