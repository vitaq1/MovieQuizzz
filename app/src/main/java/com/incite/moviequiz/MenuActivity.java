package com.incite.moviequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.incite.moviequiz.data.Data;



public class MenuActivity extends AppCompatActivity {


    FButton b1, b2, b3, b4;
    Typeface face;
    Drawable arcadeDrawable, guessDrawable, tfDrawable, shopDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /** INITIALISATION SOUND_MANAGER **/
        if(!SoundManager.isLoaded) {
            SoundManager.init(getApplicationContext());
            SoundManager.isLoaded = true;
        }
        /**  **/


        //
        if (!Data.isLoaded) Data.loadPacks(getApplicationContext());
        Data.isLoaded = true;
        //
        System.out.println(Data.getPacks().get(0).size());


        face = ResourcesCompat.getFont(this, R.font.rounds);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b1.setTypeface(face);
        b2.setTypeface(face);
        b3.setTypeface(face);
        b4.setTypeface(face);

        arcadeDrawable = ContextCompat.getDrawable(this, R.drawable.ic_cup);
        guessDrawable = ContextCompat.getDrawable(this, R.drawable.ic_hourglass);
        tfDrawable = ContextCompat.getDrawable(this, R.drawable.ic_medal);
        shopDrawable = ContextCompat.getDrawable(this, R.drawable.ic_shop);

        arcadeDrawable.setBounds(0, 0, 100, 100);
        guessDrawable.setBounds(0, 0, 100, 100);
        tfDrawable.setBounds(0, 0, 100, 100);
        shopDrawable.setBounds(0, 0, 100, 100);

        b1.setFButtonPadding(30, 0, 30, 0);
        b2.setFButtonPadding(30, 0, 30, 0);
        b3.setFButtonPadding(30, 0, 30, 0);
        b4.setFButtonPadding(30, 0, 30, 0);

        b1.setCompoundDrawables(arcadeDrawable, null, null, null);
        b2.setCompoundDrawables(guessDrawable, null, null, null);
        b3.setCompoundDrawables(tfDrawable, null, null, null);
        b4.setCompoundDrawables(shopDrawable, null, null, null);

    }

    /**
     * Activities switching
     **/

    public void startArcadeActivity(View view) {
        SoundManager.play(SoundManager.Click1);
        Intent i = new Intent(MenuActivity.this, ArcadeActivity.class);
        startActivity(i);
    }

    public void startGuessActivity(View view) {
        SoundManager.play(SoundManager.Click1);
        Intent i = new Intent(MenuActivity.this, GuessActivity.class);
        startActivity(i);
    }

    public void startTrueFalseActivity(View view) {
        SoundManager.play(SoundManager.Click1);
        Intent i = new Intent(MenuActivity.this, TrueFalseActivity.class);
        startActivity(i);
    }

    public void exitApplication(View view) {
        SoundManager.play(SoundManager.Click1);
        System.exit(0);
    }


    public void goToShop(View view) {
        SoundManager.play(SoundManager.Click1);
        Intent i = new Intent(MenuActivity.this, ShopActivity.class);
        startActivity(i);
    }
}