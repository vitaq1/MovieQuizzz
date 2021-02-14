package com.incite.moviequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.incite.moviequiz.data.Data;
import com.incite.moviequiz.data.Film;
import com.incite.moviequiz.data.FilmPack;

import org.w3c.dom.Text;

public class ArcadeActivity extends AppCompatActivity {

    LottieAnimationView sound;
    LinearLayout linearLayout;
    TextView levelTv;
    TextView moneyTv;
    Drawable lockOff = null;
    Drawable lockOn = null;
    Typeface face;

    static int levelsOffsetOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcade);

        System.out.println(Data.getPacks().get(0).size());


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        levelsOffsetOpen = (int) (0.65 * (Data.overallFilms / Data.getPacks().size()));


        /*  init */
        sound = findViewById(R.id.soundOnOff1);
        sound.setMinFrame(60);
        if(!SoundManager.isOn) sound.setFrame(60);
        else sound.setFrame(90);
        linearLayout = findViewById(R.id.mainLinearLayout);
        levelTv = findViewById(R.id.allFilmsTv1);
        moneyTv = findViewById(R.id.moneyTv1);
        lockOff = ContextCompat.getDrawable(this, R.drawable.ic_lock1);
        lockOn = ContextCompat.getDrawable(this, R.drawable.ic_lock2);
        face = ResourcesCompat.getFont(this, R.font.rounds);

        lockOff.setBounds(0, 0, dpToPixels(50), dpToPixels(50));
        lockOn.setBounds(0, 0, dpToPixels(50), dpToPixels(50));

        //


        //

        //
        /** SET PASSLEVEL TV **/
        int passed = 0;
        int all = 0;
        for (int i = 0; i < Data.getPacks().size(); i++) {
            for (int j = 0; j < Data.getPacks().get(i).size(); j++) {
                if (Data.getPacks().get(i).getFilms().get(j).isPassed()) {
                    passed++;
                }
                all++;
            }
        }
        String str = passed + " / " + all;
        levelTv.setText(str);
        moneyTv.setText(String.valueOf(Player.money));
        //


        /*  end - init */

        for (int i = 0; i < Data.getPacks().size(); i++) {

            RelativeLayout rl = new RelativeLayout(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (size.x * 0.26));
            rl.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_rect));
            lp.setMargins(dpToPixels(30), 50, dpToPixels(30), 50);
            rl.setLayoutParams(lp);
            rl.setId(i);

            //top label
            TextView tv1 = new TextView(this);
            RelativeLayout.LayoutParams tlp1 = new RelativeLayout.LayoutParams((int) (size.x - 2 * dpToPixels(30)), 60);
            String s = (i + 1) + "-й уровень";
            tv1.setText(s);
            tv1.setTextSize(17);
            tv1.setTextColor(ContextCompat.getColor(this, R.color.orange));
            tv1.setTypeface(face);
            tv1.setGravity(Gravity.CENTER_HORIZONTAL);
            tlp1.topMargin = dpToPixels(20);
            tv1.setLayoutParams(tlp1);

            //bottom label
            TextView tv2 = new TextView(this);
            RelativeLayout.LayoutParams tlp2 = new RelativeLayout.LayoutParams((int) (size.x - 2 * dpToPixels(30)), 40);

            /** SET PASSLEVEL TV **/
            int p = 0;
            int a = 0;
            for (int j = 0; j < Data.getPacks().get(i).size(); j++) {
                if (Data.getPacks().get(i).getFilms().get(j).isPassed()) {
                    p++;
                }
                a++;
            }
            String strr = p + " / " + a;
            //

            tv2.setText(strr);
            tv2.setTextSize(13);
            tv2.setTextColor(ContextCompat.getColor(this, R.color.white));
            tv2.setTypeface(face);
            tv2.setGravity(Gravity.CENTER_HORIZONTAL);
            tlp2.topMargin = dpToPixels(45);
            tv2.setLayoutParams(tlp2);

            //progress bar

            ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            RelativeLayout.LayoutParams plp = new RelativeLayout.LayoutParams((int) (size.x / 2), 60);
            plp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            plp.topMargin = dpToPixels(70);
            pb.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.progress_style));
            pb.setProgress((int) (((double) p / (double) a) * 100));
            pb.setLayoutParams(plp);

            //image

            ImageView imageView = new ImageView(this);
            RelativeLayout.LayoutParams ilp = new RelativeLayout.LayoutParams(dpToPixels(50), dpToPixels(50));
            //TODO check lock
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_lock1));
            ilp.addRule(RelativeLayout.ALIGN_LEFT);
            ilp.addRule(RelativeLayout.CENTER_VERTICAL);
            ilp.leftMargin = 45;
            imageView.setLayoutParams(ilp);

            //adding
            rl.addView(tv1);
            rl.addView(tv2);
            rl.addView(pb);
            rl.addView(imageView);

            //check if enough levels passed to unlock this level
            //rl.setClickable(false);
            String levelsToOpen = String.valueOf(i * levelsOffsetOpen - passed);
            rl.setContentDescription(levelsToOpen);
            if (passed >= i * levelsOffsetOpen) {
                //rl.setClickable(true);
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_lock2));
            }

            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(rl.getContentDescription().toString()) <= 0) {
                        Intent i = new Intent(ArcadeActivity.this, ArcadeFilmsViewActivity.class);
                        i.putExtra("packId", (view.getId()));
                        Player.currentPackId = view.getId();
                        SoundManager.play(SoundManager.Click1);
                        startActivity(i);
                    } else {
                        String s = "Чтобы открыть этот уровень, вам необходимо пройти еще " + rl.getContentDescription().toString() + " уровня(ей)";
                        Snackbar.make(view, s, Snackbar.LENGTH_LONG)
                                .show();
                    }
                }
            });

            linearLayout.addView(rl);


        }
    }

    int dpToPixels(int n) {
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (n * scale);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ArcadeActivity.this, MenuActivity.class);
        startActivity(i);
        finish();
    }


    public void startShopActivity(View view) {
        Intent i = new Intent(ArcadeActivity.this, ShopActivity.class);
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