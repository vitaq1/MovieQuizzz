package com.incite.moviequiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.incite.moviequiz.data.Data;
import com.incite.moviequiz.data.Film;

import java.util.ArrayList;
import java.util.Arrays;


public class GuessActivity extends AppCompatActivity {

    LottieAnimationView sound;
    Film currentFilm;
    Handler handler;
    FButton fifty, pass, b1, b2, b3, b4, playButton;
    TextView currentScore, recordScore, money, timeTv;
    ImageView imageView;
    LottieAnimationView clockAnim;
    Typeface face;
    int score = 0;
    String answer;

    CountDownTimer cTimer = null;
    long leftTime = 0;

    static boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);


        face = ResourcesCompat.getFont(this, R.font.rounds);
        sound = findViewById(R.id.soundOnOff4);
        sound.setMinFrame(60);
        if(!SoundManager.isOn) sound.setFrame(60);
        else sound.setFrame(90);
        if(!SoundManager.isOn) sound.setFrame(66);
        imageView = findViewById(R.id.gvt);
        fifty = findViewById(R.id.fiftyfifty);
        pass = findViewById(R.id.pass);
        currentScore = findViewById(R.id.curScore);
        recordScore = findViewById(R.id.recScore);
        money = findViewById(R.id.moneyTv4);
        clockAnim = findViewById(R.id.clockAnim);
        timeTv = findViewById(R.id.timeTv);
        b1 = findViewById(R.id.ansButton1);
        b2 = findViewById(R.id.ansButton2);
        b3 = findViewById(R.id.ansButton3);
        b4 = findViewById(R.id.ansButton4);
        playButton = findViewById(R.id.playButton);

        fifty.setTypeface(face);
        pass.setTypeface(face);
        b1.setTypeface(face);
        b2.setTypeface(face);
        b3.setTypeface(face);
        b4.setTypeface(face);
        playButton.setTypeface(face);
        clockAnim.pauseAnimation();

        b1.setOnClickListener(null);
        b2.setOnClickListener(null);
        b3.setOnClickListener(null);
        b4.setOnClickListener(null);
        fifty.setOnClickListener(null);
        pass.setOnClickListener(null);


        String s1 = "Результат: " + String.valueOf(score);
        String s2 = "Рекорд: " + String.valueOf(Player.guessRecord);
        currentScore.setText(s1);
        recordScore.setText(s2);
        money.setText(String.valueOf(Player.money));

    }

    static ArrayList<String> gameMemory;

    public void startPlay(View view) {
        score = 0;
        gameMemory = new ArrayList<>();
        playButton.setVisibility(View.INVISIBLE);
        clockAnim.playAnimation();
        nextFilm();
        startTimer(20000);
        setButtonsClickListeners();
        setHintButtonsState(fifty, 0);
        setHintButtonsState(pass, 0);

        SoundManager.play(SoundManager.Tick);
    }
    void nextFilm() {


        setAnswerButtonsState(b1, 2);
        setAnswerButtonsState(b2, 2);
        setAnswerButtonsState(b3, 2);
        setAnswerButtonsState(b4, 2);



        System.out.println(Data.overallFilms);
        if (gameMemory.size() < Data.overallFilms) {
            refreshScores();
            ArrayList<String> answers = new ArrayList<>();
            currentFilm = getRandomFilm();
            while (gameMemory.contains(currentFilm.getAnswer())) {
                System.out.println(1);
                currentFilm = getRandomFilm();
            }
            answer = currentFilm.getAnswer();
            gameMemory.add(currentFilm.getAnswer());
            System.out.println(currentFilm.getAnswer());
            isPlaying = true;

            //imageView.setImageDrawable(ContextCompat.getDrawable(this, currentFilm.getDrawableID()));
            Glide.with(getApplicationContext()).load(currentFilm.getDrawableID()).into(imageView);

            int numOfCorrectAnswer = rnd(1, 4);
            answers.add(currentFilm.getAnswer());
            String s1 = "";
            String s2 = "";
            String s3 = "";
            String s4 = "";

            s1 = (getRandomFilm().getAnswer());
            s2 = (getRandomFilm().getAnswer());
            s3 = (getRandomFilm().getAnswer());
            s4 = (getRandomFilm().getAnswer());
            while (answers.contains(s1) || answers.contains(s2) || answers.contains(s3) || answers.contains(s4)
                    || s1.equals(s2) || s1.equals(s3) || s1.equals(s4) || s2.equals(s3) || s2.equals(s4) || s3.equals(s4)) {
                System.out.println(2);
                s1 = (getRandomFilm().getAnswer());
                s2 = (getRandomFilm().getAnswer());
                s3 = (getRandomFilm().getAnswer());
                s4 = (getRandomFilm().getAnswer());
            }

            b1.setText(s1);
            b2.setText(s2);
            b3.setText(s3);
            b4.setText(s4);

            if (numOfCorrectAnswer == 1) b1.setText(currentFilm.getAnswer());
            if (numOfCorrectAnswer == 2) b2.setText(currentFilm.getAnswer());
            if (numOfCorrectAnswer == 3) b3.setText(currentFilm.getAnswer());
            if (numOfCorrectAnswer == 4) b4.setText(currentFilm.getAnswer());

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
        handler = new Handler();

        FButton currentButton = findViewById(view.getId());

        if (currentButton.getText() == currentFilm.getAnswer()) {
            setAnswerButtonsState(currentButton, 1);
            score++;
            SoundManager.play(SoundManager.Correct1);

            cancelTimer();
            startTimer(leftTime + 1000);
            handler.postDelayed(new Runnable() {
                public void run() {
                    nextFilm();
                }
            }, 500);

        } else {
            setAnswerButtonsState(currentButton, 0);
            SoundManager.play(SoundManager.Error);

            if (leftTime > 2000) {
                cancelTimer();
                startTimer(leftTime - 2000);
            }

            if (b1.getText() == currentFilm.getAnswer()) {
                setAnswerButtonsState(b1, 1);
            }
            if (b2.getText() == currentFilm.getAnswer()) {
                setAnswerButtonsState(b2, 1);
            }
            if (b3.getText() == currentFilm.getAnswer()) {
                setAnswerButtonsState(b3, 1);
            }
            if (b4.getText() == currentFilm.getAnswer()) {
                setAnswerButtonsState(b4, 1);
            }
            handler.postDelayed(new Runnable() {
                public void run() {
                    nextFilm();
                }
            }, 500);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    void setAnswerButtonsState(FButton button, int state) {
        /**
         *
         *  0 - red button
         *  1 - green button
         *  2 - default state
         * -1 - gray button
         *
         * **/
        if (state == 1) {

            button.setClickable(false);
            button.setButtonColor(ContextCompat.getColor(this, R.color.greenS));
            button.setShadowColor(ContextCompat.getColor(this, R.color.darkGreenS));
        } else if (state == 0) {

            button.setClickable(false);
            button.setButtonColor(ContextCompat.getColor(this, R.color.pinkColor));
            button.setShadowColor(ContextCompat.getColor(this, R.color.redColor));
        } else if (state == 2) {
            button.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            button.updateBackground(button.pressedDrawable);
                            view.setPadding(button.mPaddingLeft, button.mPaddingTop + button.mShadowHeight, button.mPaddingRight, button.mPaddingBottom);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Rect r = new Rect();
                            view.getLocalVisibleRect(r);
                            if (!r.contains((int) motionEvent.getX(), (int) motionEvent.getY() + 3 * button.mPaddingLeft) &&
                                    !r.contains((int) motionEvent.getX(), (int) motionEvent.getY() - 3 * button.mPaddingLeft)) {
                                button.updateBackground(button.unpressedDrawable);
                                view.setPadding(button.mPaddingLeft, button.mPaddingTop + button.mShadowHeight, button.mPaddingRight, button.mPaddingBottom + button.mShadowHeight);
                            }
                            break;
                        case MotionEvent.ACTION_OUTSIDE:
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            button.updateBackground(button.unpressedDrawable);
                            view.setPadding(button.mPaddingLeft, button.mPaddingTop + button.mShadowHeight, button.mPaddingRight, button.mPaddingBottom + button.mShadowHeight);
                            break;
                    }
                    return false;
                }
            });
            button.setClickable(true);
            button.setButtonColor(ContextCompat.getColor(this, R.color.yellowColor));
            button.setShadowColor(ContextCompat.getColor(this, R.color.orangeColor));
        } else if (state == -1) {
            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    return false;
                }
            });
            button.setClickable(false);
            button.setButtonColor(ContextCompat.getColor(this, R.color.greyColor));
            button.setShadowColor(ContextCompat.getColor(this, R.color.darkGreyColor));
        }
    }

    void setHintButtonsState(FButton button, int state) {

        /**
         *  0 - deactivated (colour) button
         *  1 - activated (grey) button
         */

        if (state == 0) {
            if (button.getId() == R.id.fiftyfifty) {
                button.setButtonColor(ContextCompat.getColor(this, R.color.greenS));
                button.setShadowColor(ContextCompat.getColor(this, R.color.darkGreenS));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFiftyFifty(view);
                    }
                });
            }
            if (button.getId() == R.id.pass) {
                button.setButtonColor(ContextCompat.getColor(this, R.color.pinkColor));
                button.setShadowColor(ContextCompat.getColor(this, R.color.redColor));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        skipFilm(view);
                    }
                });
            }
        }
        if (state == 1) {

            button.setButtonColor(ContextCompat.getColor(this, R.color.greyColor));
            button.setShadowColor(ContextCompat.getColor(this, R.color.darkGreyColor));
            button.setOnClickListener(null);
        }


    }

    void refreshScores() {
        String s1 = "Результат: " + String.valueOf(score);
        if (score > Player.guessRecord) Player.guessRecord = score;
        String s2 = "Рекорд: " + String.valueOf(Player.guessRecord);
        currentScore.setText(s1);
        recordScore.setText(s2);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelTimer();
        SoundManager.stop(SoundManager.Tick);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancelTimer();
        finish();
    }


    public void getFiftyFifty(View view) {
        setHintButtonsState(fifty, 1);
        if (b1.getText().equals(answer) || b2.getText().equals(answer)) {
            setAnswerButtonsState(b3, -1);
            setAnswerButtonsState(b4, -1);

        } else {
            setAnswerButtonsState(b1, -1);
            setAnswerButtonsState(b2, -1);
        }
    }

    public void skipFilm(View view) {
        setHintButtonsState(pass, 1);
        nextFilm();
    }

    /**************** TIMER ***************/
    void startTimer(long time) {
        cTimer = new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                leftTime = millisUntilFinished;
                int timeInSec = (int) (leftTime - 100) / 1000;
                System.out.println(leftTime);
                String s = String.valueOf(timeInSec) + " сек";
                timeTv.setText(s);
            }

            public void onFinish() {
                System.out.println("lose");
                SoundManager.stop(SoundManager.Tick);
                isPlaying = false;
                showAlertDialog(getApplicationContext());
            }
        };
        cTimer.start();

    }

    void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

    /***********************************/

    void showAlertDialog(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("Время вышло");
        builder.setMessage("Ваш результат: " + score);
        builder.setCancelable(false);

        builder.setPositiveButton("Попробовать еще раз", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startPlay(null);
                SoundManager.play(SoundManager.Click1);

                dialog.cancel();
            }
        });
        builder.setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                SoundManager.play(SoundManager.Click1);

                dialog.cancel();
            }
        });
        builder.show();
    }

    View.OnClickListener answerButtonsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SoundManager.play(SoundManager.Click1);
            checkAnswer(view);
        }
    };

    void setButtonsClickListeners() {
        b1.setOnClickListener(answerButtonsClickListener);
        b2.setOnClickListener(answerButtonsClickListener);
        b3.setOnClickListener(answerButtonsClickListener);
        b4.setOnClickListener(answerButtonsClickListener);

        fifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.play(SoundManager.Click1);
                getFiftyFifty(view);
            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.play(SoundManager.Click1);
                skipFilm(view);
            }
        });
    }


    public void startShopActivity(View view) {
        Intent i = new Intent(GuessActivity.this, ShopActivity.class);
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
