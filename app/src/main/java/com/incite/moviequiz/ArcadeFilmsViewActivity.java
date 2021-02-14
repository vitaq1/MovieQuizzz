package com.incite.moviequiz;

import android.content.Intent;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import android.transition.Fade;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.incite.moviequiz.data.Data;
import com.incite.moviequiz.data.Film;

import java.util.ArrayList;

//770 X 450 IMG
public class ArcadeFilmsViewActivity extends AppCompatActivity {

    LottieAnimationView sound;
    TextView levelTv;
    TextView moneyTv;
    ArrayList<ShapeableImageView> images;

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcadefilmsview);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        fade.excludeTarget(R.id.rlfv, true);
        fade.excludeTarget(R.id.rlag, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        sound = findViewById(R.id.soundOnOff2);
        sound.setMinFrame(60);
        if(!SoundManager.isOn) sound.setFrame(60);
        else sound.setFrame(90);

        levelTv = findViewById(R.id.allFilmsTv2);
        moneyTv = findViewById(R.id.moneyTv2);
        images = new ArrayList<>();


        Intent intent = getIntent();
        id = intent.getIntExtra("packId", 0);
        Player.isLastMain = true;
        Player.currentPackId = id;

        /** SET PASSLEVEL TV **/
        int passed = 0;
        int all = 0;
        for (int j = 0; j < Data.getPacks().get(id).size(); j++) {
            if (Data.getPacks().get(id).getFilms().get(j).isPassed()) {
                passed++;
            }
            all++;
        }
        String str = passed + " / " + all;
        levelTv.setText(str);
        moneyTv.setText(String.valueOf(Player.money));
        //


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ScrollView scrollView = findViewById(R.id.mainScrollView);


        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(2);



        int pixelsMarginSides = (int) (0.05 * size.x);
        int pixelsMarginTop = (int) (0.05 * size.x);
        int imageWidth = (int) (0.4 * size.x);
        int imageHeight = (int) (0.234 * size.x);


        for (int j = 0; j < Data.getPacks().get(id).size(); j++) {
            Film currentFilm = Data.getPacks().get(id).getFilms().get(j);



            GridLayout.LayoutParams imageViewLp = new GridLayout.LayoutParams();

            imageViewLp.rowSpec = GridLayout.spec(0, 1f);
            imageViewLp.width = imageWidth;
            imageViewLp.height = imageHeight;



            ShapeableImageView imageView = new ShapeableImageView(this);
            imageView.setTransitionName("cardViewTransition");
            imageView.setId(j);
            imageView.setContentDescription(currentFilm.getAnswer());

            imageViewLp.setMargins(pixelsMarginSides, pixelsMarginTop - 12, pixelsMarginSides, 0);
            imageView.setLayoutParams(imageViewLp);


            //imageView.setImageDrawable(ContextCompat.getDrawable(this,currentFilm.getDrawableID()));
            Glide.with(getApplicationContext()).load(currentFilm.getDrawableID()).into(imageView);



            imageView.setShapeAppearanceModel(imageView.getShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED,getResources().getDimension(R.dimen.default_corner_radius))
                    .build());


            if (Data.getPacks().get(id).getFilms().get(j).isPassed()) {
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                imageView.setColorFilter(filter);
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ArcadeFilmsViewActivity.this, ArcadeGameActivity.class);
                    i.putExtra("filmId", (view.getId()));
                    i.putExtra("packId", Player.currentPackId);
                    Player.currentFilmId = (view.getId());

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ArcadeFilmsViewActivity.this, imageView, ViewCompat.getTransitionName(imageView));
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    SoundManager.play(SoundManager.Click1);
                    startActivity(i, options.toBundle());
                    //supportFinishAfterTransition();
                }
            });
            gridLayout.addView(imageView);
            images.add(imageView);

        }
        scrollView.addView(gridLayout);
    }

    public static ColorMatrixColorFilter brightIt(int fb) {
        ColorMatrix cmB = new ColorMatrix();
        cmB.set(new float[]{
                1, 0, 0, 0, fb,
                0, 1, 0, 0, fb,
                0, 0, 1, 0, fb,
                0, 0, 0, 1, 0});

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(cmB);

        ColorMatrixColorFilter f = new ColorMatrixColorFilter(colorMatrix);
        return f;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ArcadeFilmsViewActivity.this, ArcadeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Player.isLastMain = true;
        for (int i = 0; i < images.size(); i++) {
            if (Data.getPacks().get(id).getFilms().get(i).isPassed()) {
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                images.get(i).setColorFilter(filter);
            }
        }
        int passed = 0;
        int all = 0;
        for (int j = 0; j < Data.getPacks().get(id).size(); j++) {
            if (Data.getPacks().get(id).getFilms().get(j).isPassed()) {
                passed++;
            }
            all++;
        }
        String str = passed + " / " + all;
        levelTv.setText(str);
        moneyTv.setText(String.valueOf(Player.money));
        //
    }

    public void startShopActivity(View view) {
        Intent i = new Intent(ArcadeFilmsViewActivity.this, ShopActivity.class);
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

