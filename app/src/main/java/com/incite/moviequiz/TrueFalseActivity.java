package com.incite.moviequiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.incite.moviequiz.data.Data;
import com.incite.moviequiz.data.Film;

import java.util.ArrayList;

public class TrueFalseActivity extends AppCompatActivity {

    LottieAnimationView sound;

    RelativeLayout relativeLayout;
    Film currentFilm;
    boolean correctAnswer = false;
    String answer;

    HighlightAnimation trueAnim;
    HighlightAnimation falseAnim;

    Handler handler;

    FButton trueButton, falseButton;
    TextView questionLabel, scoreTv, recordTv, moneyTv;
    ImageView imageView;
    LottieAnimationView h1, h2, h3, h4, h5;
    Typeface face;
    Drawable trueImg;
    Drawable falseImg;
    int score = 0;
    int hp = 5;

    boolean nowSwitching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truefalse);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        sound = findViewById(R.id.soundOnOff5);
        sound.setMinFrame(60);
        if(!SoundManager.isOn) sound.setFrame(60);
        else sound.setFrame(90);

        relativeLayout = findViewById(R.id.generalView);
        face = ResourcesCompat.getFont(this, R.font.rounds);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        questionLabel = findViewById(R.id.questionLabel);
        moneyTv = findViewById(R.id.moneyTv5);
        scoreTv = findViewById(R.id.curScore2);
        recordTv = findViewById(R.id.recScore2);
        imageView = findViewById(R.id.gvt2);
        h1 = findViewById(R.id.heart1);
        h2 = findViewById(R.id.heart2);
        h3 = findViewById(R.id.heart3);
        h4 = findViewById(R.id.heart4);
        h5 = findViewById(R.id.heart5);

        trueButton.setTypeface(face);
        falseButton.setTypeface(face);
        questionLabel.setTypeface(face);

        trueAnim = new HighlightAnimation(relativeLayout, Color.GREEN, 400);
        falseAnim = new HighlightAnimation(relativeLayout, Color.RED, 400);

        h1.pauseAnimation();
        h2.pauseAnimation();
        h3.pauseAnimation();
        h4.pauseAnimation();
        h5.pauseAnimation();

        h1.setMinFrame(27);
        h2.setMinFrame(27);
        h3.setMinFrame(27);
        h4.setMinFrame(27);
        h5.setMinFrame(27);

        h1.setSpeed(1.5f);
        h2.setSpeed(1.5f);
        h3.setSpeed(1.5f);
        h4.setSpeed(1.5f);
        h5.setSpeed(1.5f);

        int buttonSize = size.x / 5;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, ViewGroup.LayoutParams.MATCH_PARENT);
        h1.setLayoutParams(lp);
        h2.setLayoutParams(lp);
        h3.setLayoutParams(lp);
        h4.setLayoutParams(lp);
        h5.setLayoutParams(lp);

        trueImg = ContextCompat.getDrawable(this, R.drawable.ic_resource_true);
        falseImg = ContextCompat.getDrawable(this, R.drawable.ic_resource_false);

        trueImg.setBounds(0, 0, 80, 80);
        falseImg.setBounds(0, 0, 80, 80);
        trueButton.setFButtonPadding(30, 0, 0, 0);
        falseButton.setFButtonPadding(30, 0, 0, 0);
        trueButton.setCompoundDrawables(trueImg, null, null, null);
        falseButton.setCompoundDrawables(falseImg, null, null, null);

        String s1 = "Результат: " + String.valueOf(score);
        String s2 = "Рекорд: " + String.valueOf(Player.guessRecord);
        scoreTv.setText(s1);
        recordTv.setText(s2);
        moneyTv.setText(String.valueOf(Player.money));


        play();


    }

    static ArrayList<String> gameMemory;

    void play() {
        score = 0;
        hp = 5;
        h1.setFrame(27);
        h2.setFrame(27);
        h3.setFrame(27);
        h4.setFrame(27);
        h5.setFrame(27);

        gameMemory = new ArrayList<>();
        nextFilm();
    }

    void nextFilm() {

        if (gameMemory.size() < Data.overallFilms) {
            nowSwitching = false;
            refreshScores();
            currentFilm = getRandomFilm();
            while (gameMemory.contains(currentFilm.getAnswer())) {
                System.out.println(1);
                currentFilm = getRandomFilm();
            }
            gameMemory.add(currentFilm.getAnswer());

            //imageView.setImageDrawable(ContextCompat.getDrawable(this, currentFilm.getDrawableID()));
            Glide.with(getApplicationContext()).load(currentFilm.getDrawableID()).into(imageView);

            //case ans is true
            if (rnd(1, 10) % 3 == 0) {
                answer = currentFilm.getAnswer();
                correctAnswer = true;
            } else {
                Film fakeFilm = getRandomFilm();
                while (fakeFilm.getAnswer().equals(currentFilm.getAnswer()))
                    fakeFilm = getRandomFilm();
                answer = fakeFilm.getAnswer();
                correctAnswer = false;
            }

            String s = "Фильм называется " + "\"" + answer + "\"" + "?";
            questionLabel.setText(s);

        }
    }

    Film getRandomFilm() {
        int randomPackId = rnd(0, Data.getPacks().size() - 1);
        int randomFilmId = rnd(0, Data.getPacks().get(randomPackId).getFilms().size() - 1);
        return Data.getPacks().get(randomPackId).getFilms().get(randomFilmId);
    }

    static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public void checkAnswer(View view) {

        if (!nowSwitching) {

            nowSwitching = true;

            handler = new Handler();

            if (view.getId() == R.id.trueButton && correctAnswer) {
                score++;
                SoundManager.play(SoundManager.Correct1);
                trueAnim.animate();
            }
            if (view.getId() == R.id.trueButton && !correctAnswer) {
                minusHP();
                hp--;
                SoundManager.play(SoundManager.Error);
                falseAnim.animate();
            }
            if (view.getId() == R.id.falseButton && !correctAnswer) {
                score++;
                SoundManager.play(SoundManager.Correct1);
                trueAnim.animate();
            }
            if (view.getId() == R.id.falseButton && correctAnswer) {
                minusHP();
                hp--;
                SoundManager.play(SoundManager.Error);
                falseAnim.animate();
            }

            if (hp == 0) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        showAlertDialog(getApplicationContext());
                    }
                }, 500);
                return;
            }

            handler.postDelayed(new Runnable() {
                public void run() {
                    nextFilm();
                }
            }, 500);

        }
    }

    void minusHP() {
        if (hp == 5) {
            h5.playAnimation();
        }
        if (hp == 4) {
            h4.playAnimation();
        }
        if (hp == 3) {
            h3.playAnimation();
        }
        if (hp == 2) {
            h2.playAnimation();
        }
        if (hp == 1) {
            h1.playAnimation();
        }
    }


    void refreshScores() {
        String s1 = "Результат: " + String.valueOf(score);
        if (score > Player.guessRecord) Player.guessRecord = score;
        String s2 = "Рекорд: " + String.valueOf(Player.guessRecord);
        scoreTv.setText(s1);
        recordTv.setText(s2);
    }

    void showAlertDialog(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("Молодец!");
        builder.setMessage("Ваш результат: " + score);
        builder.setCancelable(false);

        builder.setPositiveButton("Попробовать еще раз", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                play();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void startShopActivity(View view) {
        Intent i = new Intent(TrueFalseActivity.this, ShopActivity.class);
        startActivity(i);
        finish();
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
