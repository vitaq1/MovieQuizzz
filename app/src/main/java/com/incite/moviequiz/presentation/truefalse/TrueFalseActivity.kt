package com.incite.moviequiz.presentation.truefalse

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.incite.moviequiz.domain.model.Player
import com.incite.moviequiz.R
import com.incite.moviequiz.domain.model.Data
import com.incite.moviequiz.domain.model.Film
import com.incite.moviequiz.presentation.custom_view.FButton
import com.incite.moviequiz.presentation.shop.ShopActivity

class TrueFalseActivity : AppCompatActivity() {

    private lateinit var sound: LottieAnimationView
    private lateinit var relativeLayout: RelativeLayout
    private lateinit var currentFilm: Film
    private lateinit var answer: String
    private lateinit var trueAnim: HighlightAnimation
    private lateinit var falseAnim: HighlightAnimation
    private lateinit var handler: Handler
    private lateinit var trueButton: FButton
    private lateinit var falseButton: FButton
    private lateinit var questionLabel: TextView
    private lateinit var scoreTv: TextView
    private lateinit var recordTv: TextView
    private lateinit var moneyTv: TextView
    private lateinit var imageView: ImageView
    private lateinit var h1: LottieAnimationView
    private lateinit var h2: LottieAnimationView
    private lateinit var h3: LottieAnimationView
    private lateinit var h4: LottieAnimationView
    private lateinit var h5: LottieAnimationView
    private lateinit var face: Typeface
    private lateinit var trueImg: Drawable
    private lateinit var falseImg: Drawable
    private var score = 0
    private var hp = 5
    private var nowSwitching = false
    private var correctAnswer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_truefalse)

        initViews()
        setViews()

        //if(!SoundManager.isOn) sound.setFrame(60);
        //else sound.setFrame(90);

        play()
    }

    private fun initViews(){
        sound = findViewById(R.id.soundOnOff5)
        relativeLayout = findViewById(R.id.generalView)
        face = ResourcesCompat.getFont(this, R.font.rounds)!!
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        questionLabel = findViewById(R.id.questionLabel)
        moneyTv = findViewById(R.id.moneyTv5)
        scoreTv = findViewById(R.id.curScore2)
        recordTv = findViewById(R.id.recScore2)
        imageView = findViewById(R.id.gvt2)
        h1 = findViewById(R.id.heart1)
        h2 = findViewById(R.id.heart2)
        h3 = findViewById(R.id.heart3)
        h4 = findViewById(R.id.heart4)
        h5 = findViewById(R.id.heart5)
        trueAnim = HighlightAnimation(relativeLayout, Color.GREEN, 400)
        falseAnim = HighlightAnimation(relativeLayout, Color.RED, 400)
        trueImg = ContextCompat.getDrawable(this, R.drawable.ic_resource_true)!!
        falseImg = ContextCompat.getDrawable(this, R.drawable.ic_resource_false)!!
    }

    private fun setViews(){
        sound.setMinFrame(60)
        trueButton.typeface = face
        falseButton.typeface = face
        questionLabel.typeface = face
        h1.pauseAnimation()
        h2.pauseAnimation()
        h3.pauseAnimation()
        h4.pauseAnimation()
        h5.pauseAnimation()
        h1.setMinFrame(27)
        h2.setMinFrame(27)
        h3.setMinFrame(27)
        h4.setMinFrame(27)
        h5.setMinFrame(27)
        h1.speed = 1.5f
        h2.speed = 1.5f
        h3.speed = 1.5f
        h4.speed = 1.5f
        h5.speed = 1.5f
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val buttonSize = size.x / 5
        val lp = LinearLayout.LayoutParams(buttonSize, ViewGroup.LayoutParams.MATCH_PARENT)
        h1.layoutParams = lp
        h2.layoutParams = lp
        h3.layoutParams = lp
        h4.layoutParams = lp
        h5.layoutParams = lp

        trueImg.setBounds(0, 0, 80, 80)
        falseImg.setBounds(0, 0, 80, 80)
        trueButton.setFButtonPadding(30, 0, 0, 0)
        falseButton.setFButtonPadding(30, 0, 0, 0)
        trueButton.setCompoundDrawables(trueImg, null, null, null)
        falseButton.setCompoundDrawables(falseImg, null, null, null)
        val s1 = "Результат: $score"
        val s2 = "Рекорд: " + Player.guessRecord.toString()
        scoreTv.text = s1
        recordTv.text = s2
        moneyTv.text = Player.money.toString()

    }

    private fun play() {
        score = 0
        hp = 5
        h1.frame = 27
        h2.frame = 27
        h3.frame = 27
        h4.frame = 27
        h5.frame = 27
        gameMemory = ArrayList()
        nextFilm()
    }

    private fun nextFilm() {
        if (gameMemory!!.size < Data.overallFilms) {
            nowSwitching = false
            refreshScores()
            currentFilm = randomFilm
            while (gameMemory!!.contains(currentFilm.answer)) {
                println(1)
                currentFilm = randomFilm
            }
            gameMemory!!.add(currentFilm.answer)
            Glide.with(applicationContext).load(currentFilm.drawableID).into(imageView)

            //case ans is true
            if (rnd(1, 10) % 3 == 0) {
                answer = currentFilm.answer
                correctAnswer = true
            } else {
                var fakeFilm = randomFilm
                while (fakeFilm.answer == currentFilm.answer) fakeFilm = randomFilm
                answer = fakeFilm.answer
                correctAnswer = false
            }
            val s = "Фильм называется \"$answer\"?"
            questionLabel.text = s
        }
    }

    private val randomFilm: Film
        get() {
            val randomPackId = rnd(0, Data.getPacks().size - 1)
            val randomFilmId = rnd(0, Data.getPacks()[randomPackId].films.size - 1)
            return Data.getPacks()[randomPackId].films[randomFilmId]
        }

    fun checkAnswer(view: View) {
        if (!nowSwitching) {
            nowSwitching = true
            handler = Handler()
            if (view.id == R.id.trueButton && correctAnswer) {
                score++
                //SoundManager.play(SoundManager.Correct1);
                trueAnim.animate()
            }
            if (view.id == R.id.trueButton && !correctAnswer) {
                minusHP()
                hp--
                //SoundManager.play(SoundManager.Error);
                falseAnim.animate()
            }
            if (view.id == R.id.falseButton && !correctAnswer) {
                score++
                //SoundManager.play(SoundManager.Correct1);
                trueAnim.animate()
            }
            if (view.id == R.id.falseButton && correctAnswer) {
                minusHP()
                hp--
                //SoundManager.play(SoundManager.Error);
                falseAnim.animate()
            }
            if (hp == 0) {
                handler.postDelayed({ showAlertDialog(applicationContext) }, 500)
                return
            }
            handler.postDelayed({ nextFilm() }, 500)
        }
    }

    private fun minusHP() {
        if (hp == 5) {
            h5.playAnimation()
        }
        if (hp == 4) {
            h4.playAnimation()
        }
        if (hp == 3) {
            h3.playAnimation()
        }
        if (hp == 2) {
            h2.playAnimation()
        }
        if (hp == 1) {
            h1.playAnimation()
        }
    }

    private fun refreshScores() {
        val s1 = "Результат: $score"
        if (score > Player.guessRecord) Player.guessRecord = score
        val s2 = "Рекорд: " + Player.guessRecord.toString()
        scoreTv.text = s1
        recordTv.text = s2
    }

    private fun showAlertDialog(context: Context?) {
        val builder = AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
        builder.setTitle("Молодец!")
        builder.setMessage("Ваш результат: $score")
        builder.setCancelable(false)
        builder.setPositiveButton("Попробовать еще раз") { dialog, id ->
            play()
            dialog.cancel()
        }
        builder.setNegativeButton("Выйти") { dialog, id ->
            finish()
            dialog.cancel()
        }
        builder.show()
    }

    fun startShopActivity(view: View?) {
        val i = Intent(this@TrueFalseActivity, ShopActivity::class.java)
        startActivity(i)
        finish()
    }

    fun checkSound(view: View?) {
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
        var gameMemory: ArrayList<String>? = null
        fun rnd(min: Int, max: Int): Int {
            var max = max
            max -= min
            return (Math.random() * ++max).toInt() + min
        }
    }
}