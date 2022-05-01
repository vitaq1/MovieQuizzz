package com.incite.moviequiz.presentation.arcade

import com.incite.moviequiz.util.SoundManager.play
import com.incite.moviequiz.util.SoundManager.Click1
import com.airbnb.lottie.LottieAnimationView
import android.graphics.drawable.Drawable
import android.graphics.Typeface
import android.os.Bundle
import com.incite.moviequiz.R
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.incite.moviequiz.domain.model.Player
import android.view.ViewGroup
import android.view.Gravity
import android.content.Intent
import android.graphics.Point
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.incite.moviequiz.presentation.arcade_films.ArcadeFilmsViewActivity
import com.google.android.material.snackbar.Snackbar
import com.incite.moviequiz.domain.model.Data
import com.incite.moviequiz.presentation.menu.MenuActivity
import com.incite.moviequiz.presentation.shop.ShopActivity

class ArcadeActivity : AppCompatActivity() {

    private lateinit var sound: LottieAnimationView
    private lateinit var linearLayout: LinearLayout
    private lateinit var levelTv: TextView
    private lateinit var moneyTv: TextView
    private lateinit var lockOff: Drawable
    private lateinit var lockOn: Drawable
    private lateinit var face: Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcade)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        levelsOffsetOpen = (0.65 * (Data.overallFilms / Data.getPacks().size)).toInt()


        initViews()
        setViews()

        /*if(!SoundManager.isOn) sound.setFrame(60);
        else sound.setFrame(90);*/

        //TODO fix kotlin


        /** SET PASSLEVEL TV  */
        var passed = 0
        var all = 0
        for (i in Data.getPacks().indices) {
            for (j in 0 until Data.getPacks()[i].size()) {
                if (Data.getPacks()[i].films[j].isPassed) {
                    passed++
                }
                all++
            }
        }
        val str = "$passed / $all"
        levelTv.setText(str)
        moneyTv.setText(Player.money.toString())
        //


        /*  end - init */

        for (i in Data.getPacks().indices) {
            val rl = RelativeLayout(this)
            val lp = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (size.x * 0.26).toInt()
            )
            rl.background = ContextCompat.getDrawable(this, R.drawable.ic_rect)
            lp.setMargins(dpToPixels(30), 50, dpToPixels(30), 50)
            rl.layoutParams = lp
            rl.id = i

            //top label
            val tv1 = TextView(this)
            val tlp1 = RelativeLayout.LayoutParams((size.x - 2 * dpToPixels(30)), 60)
            val s = (i + 1).toString() + "-й уровень"
            tv1.text = s
            tv1.textSize = 17f
            tv1.setTextColor(ContextCompat.getColor(this, R.color.orange))
            tv1.setTypeface(face)
            tv1.gravity = Gravity.CENTER_HORIZONTAL
            tlp1.topMargin = dpToPixels(20)
            tv1.layoutParams = tlp1

            //bottom label
            val tv2 = TextView(this)
            val tlp2 = RelativeLayout.LayoutParams((size.x - 2 * dpToPixels(30)), 40)

            /** SET PASSLEVEL TV  */
            var p = 0
            var a = 0
            for (j in 0 until Data.getPacks()[i].size()) {
                if (Data.getPacks()[i].films[j].isPassed) {
                    p++
                }
                a++
            }
            val strr = "$p / $a"
            //
            tv2.text = strr
            tv2.textSize = 13f
            tv2.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv2.setTypeface(face)
            tv2.gravity = Gravity.CENTER_HORIZONTAL
            tlp2.topMargin = dpToPixels(45)
            tv2.layoutParams = tlp2

            //progress bar
            val pb = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal)
            val plp = RelativeLayout.LayoutParams((size.x / 2), 60)
            plp.addRule(RelativeLayout.CENTER_HORIZONTAL)
            plp.topMargin = dpToPixels(70)
            pb.progressDrawable = ContextCompat.getDrawable(this, R.drawable.progress_style)
            pb.progress = (p.toDouble() / a.toDouble() * 100).toInt()
            pb.layoutParams = plp

            //image
            val imageView = ImageView(this)
            val ilp = RelativeLayout.LayoutParams(dpToPixels(50), dpToPixels(50))
            //TODO check lock
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_lock1))
            ilp.addRule(RelativeLayout.ALIGN_LEFT)
            ilp.addRule(RelativeLayout.CENTER_VERTICAL)
            ilp.leftMargin = 45
            imageView.layoutParams = ilp

            //adding
            rl.addView(tv1)
            rl.addView(tv2)
            rl.addView(pb)
            rl.addView(imageView)

            //check if enough levels passed to unlock this level
            //rl.setClickable(false);
            val levelsToOpen = (i * levelsOffsetOpen - passed).toString()
            rl.contentDescription = levelsToOpen
            if (passed >= i * levelsOffsetOpen) {
                //rl.setClickable(true);
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_lock2))
            }
            rl.setOnClickListener { view ->
                if (rl.contentDescription.toString().toInt() <= 0) {
                    val i = Intent(this, ArcadeFilmsViewActivity::class.java)
                    i.putExtra("packId", view.id)
                    Player.currentPackId = view.id
                    play(Click1)
                    startActivity(i)
                } else {
                    val s =
                        "Чтобы открыть этот уровень, вам необходимо пройти еще " + rl.contentDescription.toString() + " уровня(ей)"
                    Snackbar.make(view, s, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
            linearLayout.addView(rl)
        }
    }

    private fun initViews() {
        sound = findViewById(R.id.soundOnOff1)
        linearLayout = findViewById(R.id.mainLinearLayout)
        levelTv = findViewById(R.id.allFilmsTv1)
        moneyTv = findViewById(R.id.moneyTv1)
        lockOff = ContextCompat.getDrawable(this, R.drawable.ic_lock1)!!
        lockOn = ContextCompat.getDrawable(this, R.drawable.ic_lock2)!!
        face = ResourcesCompat.getFont(this, R.font.rounds)!!

    }

    private fun setViews() {
        sound.setMinFrame(60)
        lockOff.setBounds(0, 0, dpToPixels(50), dpToPixels(50))
        lockOn.setBounds(0, 0, dpToPixels(50), dpToPixels(50))
    }

    private fun dpToPixels(n: Int): Int {
        val scale = this.resources.displayMetrics.density
        return (n * scale).toInt()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this, MenuActivity::class.java)
        startActivity(i)
        finish()
    }

    fun startShopActivity(view: View?) {
        val i = Intent(this, ShopActivity::class.java)
        startActivity(i)
        finish()
    }

    fun checkSound(view: View?) {
        //todo kotlin
        /*if (SoundManager.isOn) {
            SoundManager.play(SoundManager.Bubble);
            SoundManager.isOn = false;
            SoundManager.stop(SoundManager.Tick);
            sound.setSpeed(-1.5f);
        } else {
            SoundManager.isOn = true;
            SoundManager.play(SoundManager.Bubble);
            sound.setSpeed(1.5f);
        }*/
        sound.playAnimation()
    }

    companion object {
        var levelsOffsetOpen = 0
    }
}