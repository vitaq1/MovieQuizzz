package com.incite.moviequiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.incite.moviequiz.data.Data;

import java.util.ArrayList;

public class ArcadeGameActivity extends AppCompatActivity {

    LottieAnimationView sound;

    static final int ANIM_DURATION = 200;
    static float OFFSET = -1000f;

    Typeface face;
    LinearLayout l1, l2, l3, l4, l11, l22, l33, l44;
    LinearLayout lettersLayout;
    RelativeLayout keyboardLayout;
    TextView levelTv;
    TextView moneyTv;
    ImageView ivt, passButton, oneLetterButton, deleteLettersButton;
    LottieAnimationView fireworksAnim;
    static char[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    ArrayList<Button> guessButtons = new ArrayList<>();
    ArrayList<Button> keyboardButtons = new ArrayList<>();
    ArrayList<String> previewedChars = new ArrayList<>();

    int answerPtr = 0;
    int filmId;
    int packId;
    String answer;
    int k = 0; // for counting opened letters by hint

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcadegame);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        fade.excludeTarget(R.id.rlfv, true);
        fade.excludeTarget(R.id.rlag, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        fireworksAnim = findViewById(R.id.fireworks_animation);

        previewedChars.add(" ");
        previewedChars.add("!");
        previewedChars.add("«");
        previewedChars.add("»");
        previewedChars.add("-");
        previewedChars.add("'");
        previewedChars.add("№");
        previewedChars.add(".");
        previewedChars.add("\"");
        previewedChars.add("+");
        previewedChars.add(":");

        sound = findViewById(R.id.soundOnOff3);
        sound.setMinFrame(60);
        if (!SoundManager.isOn) sound.setFrame(60);
        else sound.setFrame(90);

        passButton = findViewById(R.id.passButton);
        oneLetterButton = findViewById(R.id.oneButton);
        deleteLettersButton = findViewById(R.id.deleteButton);


        lettersLayout = findViewById(R.id.lettersLayout);
        lettersLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                prevFilm(null);
            }

            public void onSwipeLeft() {
                nextFilm(null);
            }
        });
        keyboardLayout = findViewById(R.id.keybordLayout);


        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);
        l11 = findViewById(R.id.l11);
        l22 = findViewById(R.id.l22);
        l33 = findViewById(R.id.l33);
        l44 = findViewById(R.id.l44);
        face = ResourcesCompat.getFont(this, R.font.rounds);
        ivt = findViewById(R.id.ivt);

        levelTv = findViewById(R.id.allFilmsTv3);
        moneyTv = findViewById(R.id.moneyTv3);

        Intent intent = getIntent();
        filmId = intent.getIntExtra("filmId", 0);
        packId = intent.getIntExtra("packId", 0);
        Player.currentFilmId = filmId;
        answer = Data.getPacks().get(packId).getFilms().get(filmId).getAnswer();

        /** SET PASSLEVEL TV **/
        int passed = 0;
        int all = 0;
        for (int j = 0; j < Data.getPacks().get(packId).size(); j++) {
            if (Data.getPacks().get(packId).getFilms().get(j).isPassed()) {
                passed++;
            }
            all++;
        }
        String str = (Player.currentFilmId + 1) + " / " + all;
        levelTv.setText(str);
        moneyTv.setText(String.valueOf(Player.money));

        //ivt.setImageDrawable(ContextCompat.getDrawable(this, Data.getPacks().get(Player.currentPackId).getFilms().get(Player.currentFilmId).getDrawableID()));
        Glide.with(getApplicationContext()).load(Data.getPacks().get(Player.currentPackId).getFilms().get(Player.currentFilmId).getDrawableID()).override(dpToPixels(350),dpToPixels(205)).fitCenter().into(ivt);
        //

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        OFFSET = -(float) (size.x);

        int buttonSize = (int) (size.x / 12.5);
        int marginSides;
        int marginSidesDefault = buttonSize / 8;

        int answerNum = 0;
        int keyboardNum = 0;

        int keyboardPtr = 0;

        /*LinearLayout.LayoutParams lettersLP = new LinearLayout.LayoutParams(size.x, lettersLayout.getHeight());
        lettersLayout.setLayoutParams(lettersLP);*/


        ///Creating guess block
        //1st case
        //ans<=9
        if (answer.length() <= 9) {
            marginSides = (int) ((size.x - answer.length() * 1.25 * buttonSize) / 2);
            for (int i = 0; i < answer.length(); i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_block));
                Glide.with(this).load(R.drawable.ic_block).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(answerClick);
                b.setId(answerNum);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                guessButtons.add(b);
                answerNum++;
                l1.addView(b);
            }
        }
        //ans 9--18
        marginSides = (int) ((size.x - 9 * 1.25 * buttonSize) / 2);
        if (answer.length() > 9 && answer.length() <= 18) {
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_block));
               Glide.with(this).load(R.drawable.ic_block).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(answerClick);
                b.setId(answerNum);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                guessButtons.add(b);
                answerNum++;
                l1.addView(b);

            }
            /** ADDING ARROW **/
            if (answer.charAt(9) != ' ') {
                View nextLineArrow = new View(this);
                LinearLayout.LayoutParams viewLp = new LinearLayout.LayoutParams(buttonSize / 2, buttonSize / 2);
                viewLp.gravity = Gravity.CENTER_VERTICAL;
                Drawable arrow = ContextCompat.getDrawable(this, R.drawable.ic_nextline);
                arrow.setBounds(0, 0, buttonSize / 2, buttonSize / 2);
                nextLineArrow.setBackgroundDrawable(arrow);
                nextLineArrow.setLayoutParams(viewLp);
                l1.addView(nextLineArrow);
            }
            /****/
            //2nd line
            marginSides = (int) ((size.x - (answer.length() - 9) * 1.25 * buttonSize) / 2);
            for (int i = 0; i < answer.length() - 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_block));
               Glide.with(this).load(R.drawable.ic_block).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(answerClick);
                b.setId(answerNum);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                guessButtons.add(b);
                answerNum++;
                l2.addView(b);
            }
        }
        //3rd case 18--27
        if (answer.length() > 18 && answer.length() <= 27) {
            marginSides = (int) ((size.x - 9 * 1.25 * buttonSize) / 2);
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_block));
               Glide.with(this).load(R.drawable.ic_block).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } /*else if (i == 9) {
                    lp.setMargins(marginSides, 0, 2 * buttonSize, 0);
                }*/ else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(answerClick);
                b.setId(answerNum);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                guessButtons.add(b);
                answerNum++;
                l1.addView(b);

            }

            /** ADDING ARROW **/
            if (answer.charAt(9) != ' ') {
                View nextLineArrow = new View(this);
                LinearLayout.LayoutParams viewLp = new LinearLayout.LayoutParams(buttonSize / 2, buttonSize / 2);
                viewLp.gravity = Gravity.CENTER_VERTICAL;
                Drawable arrow = ContextCompat.getDrawable(this, R.drawable.ic_nextline);
                arrow.setBounds(0, 0, buttonSize / 2, buttonSize / 2);
                nextLineArrow.setBackgroundDrawable(arrow);
                nextLineArrow.setLayoutParams(viewLp);
                l1.addView(nextLineArrow);
            }
            /****/

            //2nd line
            marginSides = (int) ((size.x - 9 * 1.25 * buttonSize) / 2);
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_block));
               Glide.with(this).load(R.drawable.ic_block).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(answerClick);
                b.setId(answerNum);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                guessButtons.add(b);
                answerNum++;
                l2.addView(b);
            }

            /** ADDING ARROW **/
            if (answer.charAt(18) != ' ') {
                View nextLineArrow = new View(this);
                LinearLayout.LayoutParams viewLp = new LinearLayout.LayoutParams(buttonSize / 2, buttonSize / 2);
                viewLp.gravity = Gravity.CENTER_VERTICAL;
                Drawable arrow = ContextCompat.getDrawable(this, R.drawable.ic_nextline);
                arrow.setBounds(0, 0, buttonSize / 2, buttonSize / 2);
                nextLineArrow.setBackgroundDrawable(arrow);
                nextLineArrow.setLayoutParams(viewLp);
                l2.addView(nextLineArrow);
            }
            /****/

            //3rd line
            marginSides = (int) ((size.x - (answer.length() - 18) * 1.25 * buttonSize) / 2);
            for (int i = 0; i < answer.length() - 18; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_block));
               Glide.with(this).load(R.drawable.ic_block).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(answerClick);
                b.setId(answerNum);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                guessButtons.add(b);
                answerNum++;
                l3.addView(b);

            }
        }


        ///Creating keyboard block
        int actualAnswerLength = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) != ' ') actualAnswerLength++;
        }
        System.out.println(actualAnswerLength);

        marginSides = (int) ((size.x - 9 * 1.25 * buttonSize) / 2);
        if (answer.length() <= 9) {
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keybordblock));
                Glide.with(this).load(R.drawable.ic_keybordblock).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(keyboardClick);
                b.setId(keyboardButtons.size() + 100);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                keyboardButtons.add(b);
                keyboardNum++;

                l11.addView(b);
            }
            System.out.println(keyboardButtons.size());
            System.out.println(keyboardNum);
        }
        if (answer.length() > 9 && answer.length() <= 18) {
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keybordblock));
                Glide.with(this).load(R.drawable.ic_keybordblock).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(keyboardClick);
                b.setId(keyboardButtons.size() + 100);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                keyboardButtons.add(b);
                keyboardNum++;
                l11.addView(b);
            }
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keybordblock));
                Glide.with(this).load(R.drawable.ic_keybordblock).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(keyboardClick);
                b.setId(keyboardButtons.size() + 100);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                keyboardButtons.add(b);
                keyboardNum++;
                l22.addView(b);
            }


        }
        if (answer.length() > 18 && answer.length() <= 27) {
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keybordblock));
                Glide.with(this).load(R.drawable.ic_keybordblock).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(keyboardClick);
                b.setId(keyboardButtons.size() + 100);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                keyboardButtons.add(b);
                keyboardNum++;
                l11.addView(b);
            }
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keybordblock));
                Glide.with(this).load(R.drawable.ic_keybordblock).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(keyboardClick);
                b.setId(keyboardButtons.size() + 100);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                keyboardButtons.add(b);
                keyboardNum++;
                l22.addView(b);
            }
            for (int i = 0; i < 9; i++) {
                final Button b = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(buttonSize, buttonSize);

                //b.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keybordblock));
                Glide.with(this).load(R.drawable.ic_keybordblock).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        b.setBackground(resource);
                        b.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0);
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0);

                b.setLayoutParams(lp);
                b.setOnClickListener(keyboardClick);
                b.setId(keyboardButtons.size() + 100);
                b.setTypeface(face);
                b.setPadding(0, 0, 0, 0);
                b.setTextSize((float) buttonSize / 5);
                keyboardButtons.add(b);
                keyboardNum++;
                l33.addView(b);
            }
        }


        //setContentDescription for answerButtons

        for (int i = 0; i < guessButtons.size(); i++) {
            guessButtons.get(i).setContentDescription(((CharSequence) String.valueOf(answer.charAt(i))));
        }

        //System.out.println(guessButtons);
        for (int i = 0; i < guessButtons.size(); i++) {
            System.out.println("11");

            if (guessButtons.get(i).getContentDescription().equals(" ")) {
                guessButtons.get(i).setText(" ");
                guessButtons.get(i).setVisibility(View.INVISIBLE);
            } else if (guessButtons.get(i).getContentDescription().equals("!")) {
                guessButtons.get(i).setText("!");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals("-")) {
                guessButtons.get(i).setText("-");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals("'")) {
                guessButtons.get(i).setText("'");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals("«")) {
                guessButtons.get(i).setText("«");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals("»")) {
                guessButtons.get(i).setText("»");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals(":")) {
                guessButtons.get(i).setText(":");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals(".")) {
                guessButtons.get(i).setText(".");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals("№")) {
                guessButtons.get(i).setText("№");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals("\"")) {
                guessButtons.get(i).setText("\"");
                guessButtons.get(i).setClickable(false);
            } else if (guessButtons.get(i).getContentDescription().equals("+")) {
                guessButtons.get(i).setText("+");
                guessButtons.get(i).setClickable(false);
            }
        }


        //

        /* adding keyboard letters */

        //String[] lettersDb = new String[keyboardNum];
        int a = 0;
        int b = keyboardNum - 1;
        int rand;

        //заполнение букв с ответа

        System.out.println(keyboardButtons.size());
        System.out.println(keyboardNum);
        for (int i = 0; i < answer.length(); i++) {
            Button kb;
            do {
                rand = rnd(a, b);
                kb = keyboardButtons.get(rand);
                //System.out.println(kb.getText());
            }
            while (kb.getText() != "");
            kb.setText(String.valueOf(answer.charAt(i)));
            kb.setContentDescription(String.valueOf(answer.charAt(i)));

        }
        for (int i = 0; i < keyboardNum - answer.length(); i++) {
            Button kb;
            int x = 0;
            int y = 30;
            int randomLetter = (answer.length() + 3 * i) % 30;
            do {
                rand = rnd(a, b);
                kb = keyboardButtons.get(rand);

            }
            while (kb.getText() != "");

            kb.setText(String.valueOf(alphabet[randomLetter]));
            kb.setContentDescription(String.valueOf(alphabet[randomLetter]));
            kb.setAlpha(0.999f);
        }

        for (int i = 0; i < keyboardNum; i++) {

            if (previewedChars.contains(keyboardButtons.get(i).getText())) {
                int randomLetter = (answer.length() + 3 * i) % 30;
                keyboardButtons.get(i).setText(String.valueOf(alphabet[randomLetter]));
                keyboardButtons.get(i).setContentDescription(String.valueOf(alphabet[randomLetter]));
                keyboardButtons.get(i).setAlpha(0.999f);
            }
        }

        /* adding keyboard letters */


        /** CHECK IF LEVEL ALREADY PASSED**/

        if (Data.getPacks().get(packId).getFilms().get(filmId).isPassed()) {
            for (int i = 0; i < guessButtons.size(); i++) {
                guessButtons.get(i).setText(guessButtons.get(i).getContentDescription());
            }
            k = 0;
            for (int j = 0; j < guessButtons.size(); j++) {
                for (int i = 0; i < keyboardButtons.size(); i++) {
                    if (String.valueOf(keyboardButtons.get(i).getText()).equals(String.valueOf(guessButtons.get(k).getText()))) {
                        keyboardButtons.get(i).setText("");
                        keyboardButtons.get(i).setVisibility(View.INVISIBLE);
                        break;
                    }
                }
                k++;
            }

            setEditable(false);
        }

        if (!Player.isLastMain) {
            if (Player.isLastPrev) {
                ivt.setX(-OFFSET);
                lettersLayout.setX(-OFFSET);
                keyboardLayout.setX(-OFFSET);
            } else {
                ivt.setX(OFFSET);
                lettersLayout.setX(OFFSET);
                keyboardLayout.setX(OFFSET);
            }
            ValueAnimator swipeAnim = ObjectAnimator.ofFloat(ivt, "translationX", 0);
            ValueAnimator lettersLayoutAnim = ObjectAnimator.ofFloat(lettersLayout, "translationX", 0);
            ValueAnimator keyboardLayoutAnim = ObjectAnimator.ofFloat(keyboardLayout, "translationX", 0);


            swipeAnim.setDuration(ANIM_DURATION);
            lettersLayoutAnim.setDuration(ANIM_DURATION);
            keyboardLayoutAnim.setDuration(ANIM_DURATION);

            swipeAnim.start();
            lettersLayoutAnim.start();
            keyboardLayoutAnim.start();
        }


    }

    View.OnClickListener keyboardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (answerPtr < guessButtons.size()) {
                SoundManager.play(SoundManager.Click1);

                if (keyboardButtons.get((v.getId()) - 100).getText() != "") {
                    guessButtons.get(answerPtr).setText(keyboardButtons.get((v.getId()) - 100).getText());
                    keyboardButtons.get((v.getId()) - 100).setText("");
                    keyboardButtons.get((v.getId()) - 100).setVisibility(View.INVISIBLE);


                    while (answerPtr < guessButtons.size() && guessButtons.get(answerPtr).getText() != "") {
                        answerPtr++;
                    }

                    if (isPassed()) {
                        passLevel(null);
                    }

                }
            }
        }
    };

    View.OnClickListener answerClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (guessButtons.get(v.getId()).getText() != "") {
                SoundManager.play(SoundManager.Click1);
                String temp = String.valueOf(guessButtons.get(v.getId()).getText());
                guessButtons.get(v.getId()).setText("");
                answerPtr = 0;
                while (answerPtr < guessButtons.size() && guessButtons.get(answerPtr).getText() != "") {
                    answerPtr++;
                }

                for (int i = 0; i < keyboardButtons.size(); i++) {
                    if (String.valueOf(keyboardButtons.get(i).getContentDescription()).equals(temp) && keyboardButtons.get(i).getText() == "") {

                        keyboardButtons.get(i).setText(keyboardButtons.get(i).getContentDescription());
                        keyboardButtons.get(i).setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        }
    };


    int dpToPixels(int n) {
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (n * scale);
    }

    int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }


    public void nextFilm(View view) {
        ValueAnimator swipeAnim = ObjectAnimator.ofFloat(ivt, "translationX", OFFSET);
        ValueAnimator lettersLayoutAnim = ObjectAnimator.ofFloat(lettersLayout, "translationX", OFFSET);
        ValueAnimator keyboardLayoutAnim = ObjectAnimator.ofFloat(keyboardLayout, "translationX", OFFSET);


        swipeAnim.setDuration(ANIM_DURATION / 2);
        lettersLayoutAnim.setDuration(ANIM_DURATION / 2);
        keyboardLayoutAnim.setDuration(ANIM_DURATION / 2);

        swipeAnim.start();
        lettersLayoutAnim.start();
        keyboardLayoutAnim.start();

        swipeAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if (Player.currentFilmId + 1 < Data.getPacks().get(packId).size()) {
                    Intent i = new Intent(ArcadeGameActivity.this, ArcadeGameActivity.class);
                    i.putExtra("filmId", Player.currentFilmId + 1);
                    i.putExtra("packId", Player.currentPackId);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    Player.isLastMain = false;
                    Player.isLastPrev = true;

                    startActivity(i);
                    overridePendingTransition(0, 0);
                    finish();
                } else {
                    Intent i = new Intent(ArcadeGameActivity.this, ArcadeFilmsViewActivity.class);

                    Player.isLastMain = true;
                    Player.isLastPrev = false;
                    i.putExtra("packId", Player.currentPackId);

                    startActivity(i);
                    finish();
                }
            }
        });
    }


    public void prevFilm(View view) {
        ValueAnimator swipeAnim = ObjectAnimator.ofFloat(ivt, "translationX", -OFFSET);
        ValueAnimator lettersLayoutAnim = ObjectAnimator.ofFloat(lettersLayout, "translationX", -OFFSET);
        ValueAnimator keyboardLayoutAnim = ObjectAnimator.ofFloat(keyboardLayout, "translationX", -OFFSET);

        swipeAnim.setDuration(ANIM_DURATION / 2);
        lettersLayoutAnim.setDuration(ANIM_DURATION / 2);
        keyboardLayoutAnim.setDuration(ANIM_DURATION / 2);

        swipeAnim.start();
        lettersLayoutAnim.start();
        keyboardLayoutAnim.start();
        swipeAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if (Player.currentFilmId - 1 >= 0) {
                    Intent i = new Intent(ArcadeGameActivity.this, ArcadeGameActivity.class);
                    i.putExtra("filmId", Player.currentFilmId - 1);
                    i.putExtra("packId", Player.currentPackId);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    Player.isLastMain = false;
                    Player.isLastPrev = false;

                    startActivity(i);
                    overridePendingTransition(0, 0);
                    finish();
                } else {
                    Intent i = new Intent(ArcadeGameActivity.this, ArcadeFilmsViewActivity.class);

                    Player.isLastMain = false;
                    Player.isLastPrev = false;

                    startActivity(i);
                    finish();
                }
            }
        });
    }

    public void passLevel(View view) {

        SoundManager.play(SoundManager.Correct1);

        if (!Data.getPacks().get(Player.currentPackId).getFilms().get(filmId).isPassed()) {

            for (int i = 0; i < guessButtons.size(); i++) {
                guessButtons.get(i).setText(guessButtons.get(i).getContentDescription());
            }
            k = 0;
            for (int j = 0; j < guessButtons.size(); j++) {

                for (int i = 0; i < keyboardButtons.size(); i++) {
                    if (String.valueOf(keyboardButtons.get(i).getText()).equals(String.valueOf(guessButtons.get(k).getText()))) {
                        keyboardButtons.get(i).setText("");
                        keyboardButtons.get(i).setVisibility(View.INVISIBLE);
                        break;
                    }
                }
                k++;
            }


            Player.setLevelPassed();
            Player.addMoney(10);
            fireworksAnim.playAnimation();
            fireworksAnim.setMaxFrame(40);
            fireworksAnim.setSpeed(2f);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isPassed()) nextFilm(null);
                }
            }, 1000); //2600 def

        }
    }

    public void showFirstLetter(View view) {

        SoundManager.play(SoundManager.Click1);

        if (k > guessButtons.size() - 1) k = 0;

        while (previewedChars.contains(guessButtons.get(k).getText()) && k < guessButtons.size()) {
            k++;
        }
        guessButtons.get(k).setText(guessButtons.get(k).getContentDescription());
        guessButtons.get(k).setClickable(false);

        for (int i = 0; i < keyboardButtons.size(); i++) {
            if (String.valueOf(keyboardButtons.get(i).getText()).equals(String.valueOf(guessButtons.get(k).getText()))) {
                keyboardButtons.get(i).setText("");
                keyboardButtons.get(i).setVisibility(View.INVISIBLE);
                break;
            }
        }
        k++;

        if (isPassed()) passLevel(null);

    }

    public void removeOtherLetters(View view) {
        for (int j = 0; j < keyboardButtons.size(); j++) {
            if (keyboardButtons.get(j).getAlpha() == 0.999f) {
                keyboardButtons.get(j).setText("");
                keyboardButtons.get(j).setVisibility(View.INVISIBLE);
            }
        }
    }


    boolean isPassed() {
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < guessButtons.size(); i++) {
            current.append(guessButtons.get(i).getText());
        }
        System.out.println(current.toString());
        return current.toString().equals(answer);
    }

    void setEditable(boolean isEditable) {
        if (!isEditable) {
            for (int i = 0; i < guessButtons.size(); i++) {
                guessButtons.get(i).setClickable(false);
            }
            for (int i = 0; i < keyboardButtons.size(); i++) {
                keyboardButtons.get(i).setClickable(false);
            }

            passButton.setClickable(false);
            oneLetterButton.setClickable(false);
            deleteLettersButton.setClickable(false);


        }
    }


    public void startShopActivity(View view) {
        Intent i = new Intent(ArcadeGameActivity.this, ShopActivity.class);
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