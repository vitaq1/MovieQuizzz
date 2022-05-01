package com.incite.moviequiz.presentation.guess

import com.incite.moviequiz.util.SoundManager.play
import com.incite.moviequiz.util.SoundManager.Tick
import com.airbnb.lottie.LottieAnimationView
import com.incite.moviequiz.domain.model.Film
import android.widget.TextView
import android.graphics.Typeface
import android.os.CountDownTimer
import android.os.Bundle
import com.incite.moviequiz.R
import androidx.core.content.res.ResourcesCompat
import com.incite.moviequiz.domain.model.Player
import com.bumptech.glide.Glide
import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import android.view.MotionEvent
import android.content.Intent
import android.graphics.Rect
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.incite.moviequiz.domain.model.Data
import com.incite.moviequiz.presentation.custom_view.FButton
import com.incite.moviequiz.presentation.shop.ShopActivity
import java.util.ArrayList

class GuessActivity : AppCompatActivity() {
    private lateinit var sound: LottieAnimationView
    private lateinit var currentFilm: Film
    private lateinit var handler: Handler
    private lateinit var fifty: FButton
    private lateinit var pass: FButton
    private lateinit var b1: FButton
    private lateinit var b2: FButton
    private lateinit var b3: FButton
    private lateinit var b4: FButton
    private lateinit var playButton: FButton
    private lateinit var currentScore: TextView
    private lateinit var recordScore: TextView
    private lateinit var money: TextView
    private lateinit var timeTv: TextView
    private lateinit var imageView: ImageView
    private lateinit var clockAnim: LottieAnimationView
    private lateinit var face: Typeface
    private lateinit var answer: String
    private lateinit var cTimer: CountDownTimer
    private var score = 0
    private var leftTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess)

        /* if (!isOn) sound.setFrame(60) else sound.setFrame(90)
         if (!isOn) sound.setFrame(66)*/

        initViews()
        setViews()

    }

    private fun initViews() {
        face = ResourcesCompat.getFont(this, R.font.rounds)!!
        sound = findViewById(R.id.soundOnOff4)
        imageView = findViewById(R.id.gvt)
        fifty = findViewById(R.id.fiftyfifty)
        pass = findViewById(R.id.pass)
        currentScore = findViewById(R.id.curScore)
        recordScore = findViewById(R.id.recScore)
        money = findViewById(R.id.moneyTv4)
        clockAnim = findViewById(R.id.clockAnim)
        timeTv = findViewById(R.id.timeTv)
        b1 = findViewById(R.id.ansButton1)
        b2 = findViewById(R.id.ansButton2)
        b3 = findViewById(R.id.ansButton3)
        b4 = findViewById(R.id.ansButton4)
        playButton = findViewById(R.id.playButton)
    }

    private fun setViews() {
        sound.setMinFrame(60)
        fifty.typeface = face
        pass.typeface = face
        b1.typeface = face
        b2.typeface = face
        b3.typeface = face
        b4.typeface = face
        playButton.typeface = face
        clockAnim.pauseAnimation()
        b1.setOnClickListener(null)
        b2.setOnClickListener(null)
        b3.setOnClickListener(null)
        b4.setOnClickListener(null)
        fifty.setOnClickListener(null)
        pass.setOnClickListener(null)
        val s1 = "Результат: $score"
        val s2 = "Рекорд: " + Player.guessRecord.toString()
        currentScore.text = s1
        recordScore.text = s2
        money.text = Player.money.toString()


    }

    fun startPlay(view: View?) {
        score = 0
        gameMemory = ArrayList()
        playButton.visibility = View.INVISIBLE
        clockAnim.playAnimation()
        nextFilm()
        startTimer(20000)
        setButtonsClickListeners()
        setHintButtonsState(fifty, 0)
        setHintButtonsState(pass, 0)
        play(Tick)
    }

    private fun nextFilm() {
        setAnswerButtonsState(b1, 2)
        setAnswerButtonsState(b2, 2)
        setAnswerButtonsState(b3, 2)
        setAnswerButtonsState(b4, 2)
        println(Data.overallFilms)
        if (gameMemory!!.size < Data.overallFilms) {
            refreshScores()
            val answers = ArrayList<String>()
            currentFilm = randomFilm
            while (gameMemory!!.contains(currentFilm.answer)) {
                println(1)
                currentFilm = randomFilm
            }
            answer = currentFilm.answer
            gameMemory!!.add(currentFilm.answer)
            println(currentFilm.answer)
            isPlaying = true

            Glide.with(applicationContext).load(currentFilm.drawableID).into(imageView)
            val numOfCorrectAnswer = rnd(1, 4)
            answers.add(currentFilm.answer)
            var s1 = ""
            var s2 = ""
            var s3 = ""
            var s4 = ""
            s1 = randomFilm.answer
            s2 = randomFilm.answer
            s3 = randomFilm.answer
            s4 = randomFilm.answer
            while (answers.contains(s1) || answers.contains(s2) || answers.contains(s3) || answers.contains(
                    s4
                )
                || s1 == s2 || s1 == s3 || s1 == s4 || s2 == s3 || s2 == s4 || s3 == s4
            ) {
                println(2)
                s1 = randomFilm.answer
                s2 = randomFilm.answer
                s3 = randomFilm.answer
                s4 = randomFilm.answer
            }
            b1.text = s1
            b2.text = s2
            b3.text = s3
            b4.text = s4
            if (numOfCorrectAnswer == 1) b1.text = currentFilm.answer
            if (numOfCorrectAnswer == 2) b2.text = currentFilm.answer
            if (numOfCorrectAnswer == 3) b3.text = currentFilm.answer
            if (numOfCorrectAnswer == 4) b4.text = currentFilm.answer
        }
    }

    private val randomFilm: Film
        get() {
            val randomPackId = rnd(0, Data.getPacks().size - 1)
            val randomFilmId = rnd(0, Data.getPacks()[randomPackId].films.size - 1)
            return Data.getPacks()[randomPackId].films[randomFilmId]
        }

    fun checkAnswer(view: View) {
        handler = Handler()
        val currentButton = findViewById<FButton>(view.id)
        if (currentButton.text === currentFilm.answer) {
            setAnswerButtonsState(currentButton, 1)
            score++
            //SoundManager.play(SoundManager.Correct1);
            cancelTimer()
            startTimer(leftTime + 1000)
            handler.postDelayed({ nextFilm() }, 500)
        } else {
            setAnswerButtonsState(currentButton, 0)
            //SoundManager.play(SoundManager.Error);
            if (leftTime > 2000) {
                cancelTimer()
                startTimer(leftTime - 2000)
            }
            if (b1.text === currentFilm.answer) {
                setAnswerButtonsState(b1, 1)
            }
            if (b2.text === currentFilm.answer) {
                setAnswerButtonsState(b2, 1)
            }
            if (b3.text === currentFilm.answer) {
                setAnswerButtonsState(b3, 1)
            }
            if (b4.text === currentFilm.answer) {
                setAnswerButtonsState(b4, 1)
            }
            handler.postDelayed({ nextFilm() }, 500)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setAnswerButtonsState(button: FButton, state: Int) {
        /**
         *
         * 0 - red button
         * 1 - green button
         * 2 - default state
         * -1 - gray button
         *
         */
        if (state == 1) {
            button.isClickable = false
            button.buttonColor = ContextCompat.getColor(this, R.color.greenS)
            button.shadowColor = ContextCompat.getColor(this, R.color.darkGreenS)
        } else if (state == 0) {
            button.isClickable = false
            button.buttonColor = ContextCompat.getColor(this, R.color.pinkColor)
            button.shadowColor = ContextCompat.getColor(this, R.color.redColor)
        } else if (state == 2) {
            button.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        button.updateBackground(button.pressedDrawable)
                        view.setPadding(
                            button.mPaddingLeft,
                            button.mPaddingTop + button.mShadowHeight,
                            button.mPaddingRight,
                            button.mPaddingBottom
                        )
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val r = Rect()
                        view.getLocalVisibleRect(r)
                        if (!r.contains(
                                motionEvent.x.toInt(), motionEvent.y
                                    .toInt() + 3 * button.mPaddingLeft
                            ) &&
                            !r.contains(
                                motionEvent.x.toInt(), motionEvent.y
                                    .toInt() - 3 * button.mPaddingLeft
                            )
                        ) {
                            button.updateBackground(button.unpressedDrawable)
                            view.setPadding(
                                button.mPaddingLeft,
                                button.mPaddingTop + button.mShadowHeight,
                                button.mPaddingRight,
                                button.mPaddingBottom + button.mShadowHeight
                            )
                        }
                    }
                    MotionEvent.ACTION_OUTSIDE, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                        button.updateBackground(button.unpressedDrawable)
                        view.setPadding(
                            button.mPaddingLeft,
                            button.mPaddingTop + button.mShadowHeight,
                            button.mPaddingRight,
                            button.mPaddingBottom + button.mShadowHeight
                        )
                    }
                }
                false
            }
            button.isClickable = true
            button.buttonColor = ContextCompat.getColor(this, R.color.yellowColor)
            button.shadowColor = ContextCompat.getColor(this, R.color.orangeColor)
        } else if (state == -1) {
            button.setOnTouchListener { view, motionEvent -> false }
            button.isClickable = false
            button.buttonColor = ContextCompat.getColor(this, R.color.greyColor)
            button.shadowColor = ContextCompat.getColor(this, R.color.darkGreyColor)
        }
    }

    private fun setHintButtonsState(button: FButton, state: Int) {
        /**
         * 0 - deactivated (colour) button
         * 1 - activated (grey) button
         */
        if (state == 0) {
            if (button.id == R.id.fiftyfifty) {
                button.buttonColor = ContextCompat.getColor(this, R.color.greenS)
                button.shadowColor = ContextCompat.getColor(this, R.color.darkGreenS)
                button.setOnClickListener { view -> getFiftyFifty(view) }
            }
            if (button.id == R.id.pass) {
                button.buttonColor = ContextCompat.getColor(this, R.color.pinkColor)
                button.shadowColor = ContextCompat.getColor(this, R.color.redColor)
                button.setOnClickListener { view -> skipFilm(view) }
            }
        }
        if (state == 1) {
            button.buttonColor = ContextCompat.getColor(this, R.color.greyColor)
            button.shadowColor = ContextCompat.getColor(this, R.color.darkGreyColor)
            button.setOnClickListener(null)
        }
    }

    private fun refreshScores() {
        val s1 = "Результат: $score"
        if (score > Player.guessRecord) Player.guessRecord = score
        val s2 = "Рекорд: " + Player.guessRecord.toString()
        currentScore.text = s1
        recordScore.text = s2
    }

    override fun onBackPressed() {
        super.onBackPressed()
        cancelTimer()
        //SoundManager.stop(SoundManager.Tick);
        finish()
    }

    override fun onStop() {
        super.onStop()
        cancelTimer()
        finish()
    }

    fun getFiftyFifty(view: View?) {
        setHintButtonsState(fifty, 1)
        if (b1.text == answer || b2.text == answer) {
            setAnswerButtonsState(b3, -1)
            setAnswerButtonsState(b4, -1)
        } else {
            setAnswerButtonsState(b1, -1)
            setAnswerButtonsState(b2, -1)
        }
    }

    fun skipFilm(view: View?) {
        setHintButtonsState(pass, 1)
        nextFilm()
    }

    /**************** TIMER  */
    private fun startTimer(time: Long) {
        cTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                leftTime = millisUntilFinished
                val timeInSec = (leftTime - 100).toInt() / 1000
                println(leftTime)
                val s = "$timeInSec сек"
                timeTv.text = s
            }

            override fun onFinish() {
                println("lose")
                //SoundManager.stop(SoundManager.Tick);
                isPlaying = false
                showAlertDialog(applicationContext)
            }
        }
        cTimer.start()
    }

    private fun cancelTimer() {
        if (cTimer != null) cTimer.cancel()
    }

    /** */
    fun showAlertDialog(context: Context?) {
        val builder = AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
        builder.setTitle("Время вышло")
        builder.setMessage("Ваш результат: $score")
        builder.setCancelable(false)
        builder.setPositiveButton("Попробовать еще раз") { dialog, id ->
            startPlay(null)
            //SoundManager.play(SoundManager.Click1);
            dialog.cancel()
        }
        builder.setNegativeButton("Выйти") { dialog, id ->
            finish()
            //SoundManager.play(SoundManager.Click1);
            dialog.cancel()
        }
        builder.show()
    }

    var answerButtonsClickListener =
        View.OnClickListener { view -> //SoundManager.play(SoundManager.Click1);
            checkAnswer(view)
        }

    private fun setButtonsClickListeners() {
        b1.setOnClickListener(answerButtonsClickListener)
        b2.setOnClickListener(answerButtonsClickListener)
        b3.setOnClickListener(answerButtonsClickListener)
        b4.setOnClickListener(answerButtonsClickListener)
        fifty.setOnClickListener { view -> //SoundManager.play(SoundManager.Click1);
            getFiftyFifty(view)
        }
        pass.setOnClickListener { view -> //SoundManager.play(SoundManager.Click1);
            skipFilm(view)
        }
    }

    fun startShopActivity(view: View?) {
        val i = Intent(this@GuessActivity, ShopActivity::class.java)
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
        var isPlaying = false
        var gameMemory: ArrayList<String>? = null
        fun rnd(min: Int, max: Int): Int {
            var max = max
            max -= min
            return (Math.random() * ++max).toInt() + min
        }
    }
}