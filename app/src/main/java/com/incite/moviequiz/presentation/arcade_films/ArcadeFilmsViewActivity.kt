package com.incite.moviequiz.presentation.arcade_films

import com.incite.moviequiz.util.SoundManager.isOn
import com.incite.moviequiz.util.SoundManager.play
import com.incite.moviequiz.util.SoundManager.Click1
import com.airbnb.lottie.LottieAnimationView
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import android.os.Bundle
import com.incite.moviequiz.R
import android.content.Intent
import android.widget.ScrollView
import com.bumptech.glide.Glide
import com.google.android.material.shape.CornerFamily
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Point
import android.net.Uri
import android.transition.Fade
import android.view.View
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import com.incite.moviequiz.presentation.arcade_game.ArcadeGameActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.incite.moviequiz.domain.model.DataLoader
import com.incite.moviequiz.domain.model.Player
import com.incite.moviequiz.presentation.arcade.ArcadeActivity
import com.incite.moviequiz.presentation.shop.ShopActivity
import com.incite.moviequiz.util.SoundManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
//770 X 450 IMG
class ArcadeFilmsViewActivity : AppCompatActivity() {

    @Inject
    lateinit var dataLoader: DataLoader
    private var player: Player? = null

    private lateinit var sound: LottieAnimationView
    private lateinit var levelTv: TextView
    private lateinit var moneyTv: TextView
    private lateinit var images: ArrayList<ShapeableImageView>
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcadefilmsview)

        setAnimations()
        initViews()
        setViews()

        player = dataLoader.repo.getPlayer()

        if (!isOn) sound.frame = 60 else sound.frame = 90

        id = intent.getIntExtra("packId", 0)

        /** SET PASSLEVEL TV  */


        var passed = 0
        var all = 0
        for (j in 0 until dataLoader.packs[id].films.size) {
            if (dataLoader.packs[id].films[j].completed) {
                passed++
            }
            all++
        }
        val str = "$passed / $all"
        levelTv.text = str
        moneyTv.text = player?.money.toString()

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val scrollView = findViewById<ScrollView>(R.id.mainScrollView)
        val gridLayout = GridLayout(this)
        gridLayout.columnCount = 2
        val pixelsMarginSides = (0.05 * size.x).toInt()
        val pixelsMarginTop = (0.05 * size.x).toInt()
        val imageWidth = (0.4 * size.x).toInt()
        val imageHeight = (0.234 * size.x).toInt()
        for (j in 0 until dataLoader.packs[id].films.size) {
            val currentFilm = dataLoader.packs[id].films[j]
            val imageViewLp = GridLayout.LayoutParams()
            imageViewLp.rowSpec = GridLayout.spec(0, 1f)
            imageViewLp.width = imageWidth
            imageViewLp.height = imageHeight
            val imageView = ShapeableImageView(this)
            imageView.transitionName = "cardViewTransition"
            imageView.id = j
            imageView.contentDescription = currentFilm.name
            imageViewLp.setMargins(pixelsMarginSides, pixelsMarginTop - 12, pixelsMarginSides, 0)
            imageView.layoutParams = imageViewLp

            Glide.with(applicationContext).load(Uri.parse(currentFilm.image)).into(imageView)
            imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
                .toBuilder()
                .setAllCorners(
                    CornerFamily.ROUNDED,
                    resources.getDimension(R.dimen.default_corner_radius)
                )
                .build()
            if (dataLoader.packs[id].films[j].completed) {
                val matrix = ColorMatrix()
                matrix.setSaturation(0f)
                val filter = ColorMatrixColorFilter(matrix)
                imageView.colorFilter = filter
            }
            imageView.setOnClickListener { view ->
                val i = Intent(this@ArcadeFilmsViewActivity, ArcadeGameActivity::class.java)
                i.putExtra("filmId", view.id)
                i.putExtra("packId", id)
                i.putExtra("isLastMain", true)
                i.putExtra("isLastPrev", false)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@ArcadeFilmsViewActivity,
                    imageView,
                    ViewCompat.getTransitionName(imageView)!!
                )
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                play(Click1)
                startActivity(i, options.toBundle())
            }
            gridLayout.addView(imageView)
            images.add(imageView)
        }
        scrollView.addView(gridLayout)
    }

    private fun setAnimations(){
        val fade = Fade()
        fade.excludeTarget(android.R.id.statusBarBackground, true)
        fade.excludeTarget(android.R.id.navigationBarBackground, true)
        fade.excludeTarget(R.id.rlfv, true)
        fade.excludeTarget(R.id.rlag, true)
        window.enterTransition = fade
        window.exitTransition = fade
    }

    private fun initViews(){
        sound = findViewById(R.id.soundOnOff2)
        levelTv = findViewById(R.id.allFilmsTv2)
        moneyTv = findViewById(R.id.moneyTv2)

    }

    private fun setViews(){
        sound.setMinFrame(60)
        images = ArrayList()

    }


    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this@ArcadeFilmsViewActivity, ArcadeActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        for (i in images.indices) {
            if (dataLoader.packs[id].films[i].completed) {
                val matrix = ColorMatrix()
                matrix.setSaturation(0f)
                val filter = ColorMatrixColorFilter(matrix)
                images[i].colorFilter = filter
            }
        }
        var passed = 0
        var all = 0
        for (j in 0 until dataLoader.packs[id].films.size) {
            if (dataLoader.packs[id].films[j].completed) {
                passed++
            }
            all++
        }
        val str = "$passed / $all"
        levelTv.text = str
        moneyTv.text = player?.money.toString()
    }

    fun startShopActivity(view: View?) {
        val i = Intent(this@ArcadeFilmsViewActivity, ShopActivity::class.java)
        startActivity(i)
        finish()
    }

    fun checkSound(view: View?) {
        if (SoundManager.isOn) {
            SoundManager.play(SoundManager.Bubble);
            SoundManager.isOn = false;
            SoundManager.stop(SoundManager.Tick);
            sound.speed = -1.5f
        } else {
            SoundManager.isOn = true;
            SoundManager.play(SoundManager.Bubble);
            sound.speed = 1.5f
        }
        sound.playAnimation()
    }

}