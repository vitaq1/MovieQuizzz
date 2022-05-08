package com.incite.moviequiz.presentation.arcade_game

import android.animation.Animator
import com.incite.moviequiz.util.SoundManager.isOn
import com.incite.moviequiz.util.SoundManager.play
import com.incite.moviequiz.util.SoundManager.Click1
import com.incite.moviequiz.util.SoundManager.Correct1
import com.airbnb.lottie.LottieAnimationView
import android.graphics.Typeface
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import android.content.Intent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.core.content.ContextCompat
import android.animation.ValueAnimator
import android.animation.ObjectAnimator
import android.animation.AnimatorListenerAdapter
import android.graphics.Point
import android.net.Uri
import android.os.Handler
import android.transition.Fade
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.request.transition.Transition
import com.incite.moviequiz.R
import com.incite.moviequiz.domain.model.DataLoader
import com.incite.moviequiz.domain.model.Film
import com.incite.moviequiz.domain.model.Player
import com.incite.moviequiz.presentation.arcade_films.ArcadeFilmsViewActivity
import com.incite.moviequiz.presentation.shop.ShopActivity
import com.incite.moviequiz.util.SoundManager
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class ArcadeGameActivity : AppCompatActivity() {

    @Inject
    lateinit var dataLoader: DataLoader
    private var player: Player? = null
    private var film: Film? = null

    private val ANIM_DURATION = 200
    private var OFFSET = -1000f

    private lateinit var sound: LottieAnimationView
    private lateinit var face: Typeface
    private lateinit var l1: LinearLayout
    private lateinit var l2: LinearLayout
    private lateinit var l3: LinearLayout
    private lateinit var l4: LinearLayout
    private lateinit var l11: LinearLayout
    private lateinit var l22: LinearLayout
    private lateinit var l33: LinearLayout
    private lateinit var l44: LinearLayout
    private lateinit var lettersLayout: LinearLayout
    private lateinit var keyboardLayout: RelativeLayout
    private lateinit var levelTv: TextView
    private lateinit var moneyTv: TextView
    private lateinit var ivt: ImageView
    private lateinit var passButton: ImageView
    private lateinit var oneLetterButton: ImageView
    private lateinit var deleteLettersButton: ImageView
    private lateinit var fireworksAnim: LottieAnimationView
    private var alphabet = charArrayOf(
        'а',
        'б',
        'в',
        'г',
        'д',
        'е',
        'ж',
        'з',
        'и',
        'й',
        'к',
        'л',
        'м',
        'н',
        'о',
        'п',
        'р',
        'с',
        'т',
        'у',
        'ф',
        'х',
        'ц',
        'ш',
        'щ',
        'ъ',
        'ы',
        'ь',
        'э',
        'ю',
        'я'
    )
    var guessButtons = ArrayList<Button>()
    var keyboardButtons = ArrayList<Button>()
    var previewedChars = ArrayList<String>()
    private var answerPtr = 0
    var filmId = 0
    var packId = 0
    var isLastMain = false
    var isLastPrev = false
    private var answer: String = ""
    var k = 0 // for counting opened letters by hint

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcadegame)

        setAnimations()
        initViews()
        setViews()
        player = dataLoader.repo.getPlayer()

        previewedChars.addAll(arrayOf(" ", "!", "«", "»", "-", "'", "№", ".", "\"", "+", ":"))
        if (!isOn) sound.frame = 60 else sound.frame = 90

        filmId = intent.getIntExtra("filmId", 0)
        packId = intent.getIntExtra("packId", 0)
        film = dataLoader.repo.getFilmById(packId * 20 + filmId)
        isLastMain = intent.getBooleanExtra("isLastMain", false)
        isLastPrev = intent.getBooleanExtra("isLastPrev", false)

        println("filmId: " + filmId)
        println("packId: " + packId)
        println("film: " + film)


        answer = dataLoader.packs[packId].films[filmId].name

        /** SET PASSLEVEL TV  */
        var passed = 0
        var all = 0
        for (j in 0 until dataLoader.packs[packId].films.size) {
            if (dataLoader.packs[packId].films[j].completed) {
                passed++
            }
            all++
        }
        val str = (filmId + 1).toString() + " / " + all
        levelTv.text = str
        moneyTv.text = player?.money.toString()
        Glide.with(applicationContext)
            .load(Uri.parse(dataLoader.packs[packId].films[filmId].image))
            .override(dpToPixels(350), dpToPixels(205)).fitCenter().into(ivt)
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        OFFSET = (-size.x).toFloat()
        val buttonSize = (size.x / 12.5).toInt()
        var marginSides: Int
        val marginSidesDefault = buttonSize / 8
        var answerNum = 0
        var keyboardNum = 0
        val keyboardPtr = 0


        ///Creating guess block
        //1st case
        //ans<=9
        if (answer.length <= 9) {
            marginSides = ((size.x - answer.length * 1.25 * buttonSize) / 2).toInt()
            for (i in answer.indices) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_block).into(object : CustomTarget<Drawable?>() {

                    override fun onLoadCleared(placeholder: Drawable?) {}

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        b.background = resource
                        b.buildDrawingCache()
                    }
                })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(answerClick)
                b.id = answerNum
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                guessButtons.add(b)
                answerNum++
                l1.addView(b)
            }
        }
        //ans 9--18
        marginSides = ((size.x - 9 * 1.25 * buttonSize) / 2).toInt()
        if (answer.length in 10..18) {
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_block).into(object : CustomTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        b.background = resource
                        b.buildDrawingCache()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(answerClick)
                b.id = answerNum
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                guessButtons.add(b)
                answerNum++
                l1.addView(b)
            }
            /** ADDING ARROW  */
            if (answer.get(9) != ' ') {
                val nextLineArrow = View(this)
                val viewLp = LinearLayout.LayoutParams(buttonSize / 2, buttonSize / 2)
                viewLp.gravity = Gravity.CENTER_VERTICAL
                val arrow = ContextCompat.getDrawable(this, R.drawable.ic_nextline)
                arrow!!.setBounds(0, 0, buttonSize / 2, buttonSize / 2)
                nextLineArrow.setBackgroundDrawable(arrow)
                nextLineArrow.layoutParams = viewLp
                l1.addView(nextLineArrow)
            }
            /** */
            //2nd line
            marginSides = ((size.x - (answer.length - 9) * 1.25 * buttonSize) / 2).toInt()
            for (i in 0 until answer.length - 9) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_block).into(object : CustomTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        b.background = resource
                        b.buildDrawingCache()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(answerClick)
                b.id = answerNum
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                guessButtons.add(b)
                answerNum++
                l2.addView(b)
            }
        }
        //3rd case 18--27
        if (answer.length > 18 && answer.length <= 27) {
            marginSides = ((size.x - 9 * 1.25 * buttonSize) / 2).toInt()
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_block).into(object : CustomTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        b.background = resource
                        b.buildDrawingCache()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(answerClick)
                b.id = answerNum
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                guessButtons.add(b)
                answerNum++
                l1.addView(b)
            }
            /** ADDING ARROW  */
            if (answer.get(9) != ' ') {
                val nextLineArrow = View(this)
                val viewLp = LinearLayout.LayoutParams(buttonSize / 2, buttonSize / 2)
                viewLp.gravity = Gravity.CENTER_VERTICAL
                val arrow = ContextCompat.getDrawable(this, R.drawable.ic_nextline)
                arrow!!.setBounds(0, 0, buttonSize / 2, buttonSize / 2)
                nextLineArrow.setBackgroundDrawable(arrow)
                nextLineArrow.layoutParams = viewLp
                l1.addView(nextLineArrow)
            }
            /** */

            //2nd line
            marginSides = ((size.x - 9 * 1.25 * buttonSize) / 2).toInt()
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_block).into(object : CustomTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        b.background = resource
                        b.buildDrawingCache()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(answerClick)
                b.id = answerNum
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                guessButtons.add(b)
                answerNum++
                l2.addView(b)
            }
            /** ADDING ARROW  */
            if (answer.get(18) != ' ') {
                val nextLineArrow = View(this)
                val viewLp = LinearLayout.LayoutParams(buttonSize / 2, buttonSize / 2)
                viewLp.gravity = Gravity.CENTER_VERTICAL
                val arrow = ContextCompat.getDrawable(this, R.drawable.ic_nextline)
                arrow!!.setBounds(0, 0, buttonSize / 2, buttonSize / 2)
                nextLineArrow.setBackgroundDrawable(arrow)
                nextLineArrow.layoutParams = viewLp
                l2.addView(nextLineArrow)
            }
            /** */

            //3rd line
            marginSides = ((size.x - (answer.length - 18) * 1.25 * buttonSize) / 2).toInt()
            for (i in 0 until answer.length - 18) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_block).into(object : CustomTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        b.background = resource
                        b.buildDrawingCache()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(answerClick)
                b.id = answerNum
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                guessButtons.add(b)
                answerNum++
                l3.addView(b)
            }
        }

        ///Creating keyboard block
        var actualAnswerLength = 0
        for (i in 0 until answer.length) {
            if (answer.get(i) != ' ') actualAnswerLength++
        }
        println(actualAnswerLength)
        marginSides = ((size.x - 9 * 1.25 * buttonSize) / 2).toInt()
        if (answer.length <= 9) {
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_keybordblock)
                    .into(object : CustomTarget<Drawable?>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable?>?
                        ) {
                            b.background = resource
                            b.buildDrawingCache()
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(keyboardClick)
                b.id = keyboardButtons.size + 100
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                keyboardButtons.add(b)
                keyboardNum++
                l11.addView(b)
            }
        }
        if (answer.length in 10..18) {
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_keybordblock)
                    .into(object : CustomTarget<Drawable?>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable?>?
                        ) {
                            b.background = resource
                            b.buildDrawingCache()
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(keyboardClick)
                b.id = keyboardButtons.size + 100
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                keyboardButtons.add(b)
                keyboardNum++
                l11.addView(b)
            }
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_keybordblock)
                    .into(object : CustomTarget<Drawable?>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable?>?
                        ) {
                            b.background = resource
                            b.buildDrawingCache()
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(keyboardClick)
                b.id = keyboardButtons.size + 100
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                keyboardButtons.add(b)
                keyboardNum++
                l22.addView(b)
            }
        }
        if (answer.length in 19..27) {
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_keybordblock)
                    .into(object : CustomTarget<Drawable?>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable?>?
                        ) {
                            b.background = resource
                            b.buildDrawingCache()
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(keyboardClick)
                b.id = keyboardButtons.size + 100
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                keyboardButtons.add(b)
                keyboardNum++
                l11.addView(b)
            }
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_keybordblock)
                    .into(object : CustomTarget<Drawable?>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable?>?
                        ) {
                            b.background = resource
                            b.buildDrawingCache()
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(keyboardClick)
                b.id = keyboardButtons.size + 100
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                keyboardButtons.add(b)
                keyboardNum++
                l22.addView(b)
            }
            for (i in 0..8) {
                val b = Button(this)
                val lp = LinearLayout.LayoutParams(buttonSize, buttonSize)
                Glide.with(this).load(R.drawable.ic_keybordblock)
                    .into(object : CustomTarget<Drawable?>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable?>?
                        ) {
                            b.background = resource
                            b.buildDrawingCache()
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
                if (i == 0) {
                    lp.setMargins(marginSides, 0, marginSidesDefault, 0)
                } else lp.setMargins(marginSidesDefault, 0, marginSidesDefault, 0)
                b.layoutParams = lp
                b.setOnClickListener(keyboardClick)
                b.id = keyboardButtons.size + 100
                b.typeface = face
                b.setPadding(0, 0, 0, 0)
                b.textSize = buttonSize.toFloat() / 5
                keyboardButtons.add(b)
                keyboardNum++
                l33.addView(b)
            }
        }


        //setContentDescription for answerButtons
        for (i in guessButtons.indices) {
            guessButtons[i].contentDescription = answer.get(i).toString()
        }
        for (i in guessButtons.indices) {
            if (guessButtons[i].contentDescription == " ") {
                guessButtons[i].text = " "
                guessButtons[i].visibility = View.INVISIBLE
            } else if (guessButtons[i].contentDescription == "!") {
                guessButtons[i].text = "!"
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == "-") {
                guessButtons[i].text = "-"
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == "'") {
                guessButtons[i].text = "'"
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == "«") {
                guessButtons[i].text = "«"
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == "»") {
                guessButtons[i].text = "»"
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == ":") {
                guessButtons[i].text = ":"
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == ".") {
                guessButtons[i].text = "."
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == "№") {
                guessButtons[i].text = "№"
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == "\"") {
                guessButtons[i].text = "\""
                guessButtons[i].isClickable = false
            } else if (guessButtons[i].contentDescription == "+") {
                guessButtons[i].text = "+"
                guessButtons[i].isClickable = false
            }
        }


        /* adding keyboard letters */
        val a = 0
        val b = keyboardNum - 1
        var rand: Int

        //заполнение букв с ответа
        for (i in 0 until answer.length) {
            var kb: Button
            do {
                rand = rnd(a, b)
                kb = keyboardButtons[rand]
            } while (kb.text !== "")
            kb.text = answer.get(i).toString()
            kb.contentDescription = answer.get(i).toString()
        }
        for (i in 0 until keyboardNum - answer.length) {
            var kb: Button
            val x = 0
            val y = 30
            val randomLetter = (answer.length + 3 * i) % 30
            do {
                rand = rnd(a, b)
                kb = keyboardButtons[rand]
            } while (kb.text !== "")
            kb.text = alphabet[randomLetter].toString()
            kb.contentDescription = alphabet[randomLetter].toString()
            kb.alpha = 0.999f
        }
        for (i in 0 until keyboardNum) {
            if (previewedChars.contains(keyboardButtons[i].text)) {
                val randomLetter = (answer.length + 3 * i) % 30
                keyboardButtons[i].text = alphabet[randomLetter].toString()
                keyboardButtons[i].contentDescription = alphabet[randomLetter].toString()
                keyboardButtons[i].alpha = 0.999f
            }
        }

        /* adding keyboard letters */
        /** CHECK IF LEVEL ALREADY PASSED */
        if (dataLoader.packs[packId].films[filmId].completed) {
            for (i in guessButtons.indices) {
                guessButtons[i].text = guessButtons[i].contentDescription
            }
            k = 0
            for (j in guessButtons.indices) {
                for (i in keyboardButtons.indices) {
                    if (keyboardButtons[i].text.toString() == guessButtons[k].text.toString()) {
                        keyboardButtons[i].text = ""
                        keyboardButtons[i].visibility = View.INVISIBLE
                        break
                    }
                }
                k++
            }
            setEditable(false)
        }


        if (!isLastMain) {
            if (isLastPrev) {
                ivt.x = -OFFSET
                lettersLayout.x = -OFFSET
                keyboardLayout.x = -OFFSET
            } else {
                ivt.x = OFFSET
                lettersLayout.x = OFFSET
                keyboardLayout.x = OFFSET
            }
            val swipeAnim: ValueAnimator = ObjectAnimator.ofFloat(ivt, "translationX", 0f)
            val lettersLayoutAnim: ValueAnimator =
                ObjectAnimator.ofFloat(lettersLayout, "translationX", 0f)
            val keyboardLayoutAnim: ValueAnimator =
                ObjectAnimator.ofFloat(keyboardLayout, "translationX", 0f)
            swipeAnim.duration = ANIM_DURATION.toLong()
            lettersLayoutAnim.duration = ANIM_DURATION.toLong()
            keyboardLayoutAnim.duration = ANIM_DURATION.toLong()
            swipeAnim.start()
            lettersLayoutAnim.start()
            keyboardLayoutAnim.start()
        }
    }


    private fun setAnimations() {
        val fade = Fade()
        fade.excludeTarget(android.R.id.statusBarBackground, true)
        fade.excludeTarget(android.R.id.navigationBarBackground, true)
        fade.excludeTarget(R.id.rlfv, true)
        fade.excludeTarget(R.id.rlag, true)
        window.enterTransition = fade
        window.exitTransition = fade
    }

    private fun initViews() {

        fireworksAnim = findViewById(R.id.fireworks_animation)
        sound = findViewById(R.id.soundOnOff3)
        passButton = findViewById(R.id.passButton)
        oneLetterButton = findViewById(R.id.oneButton)
        deleteLettersButton = findViewById(R.id.deleteButton)
        lettersLayout = findViewById(R.id.lettersLayout)
        keyboardLayout = findViewById(R.id.keybordLayout)
        l1 = findViewById(R.id.l1)
        l2 = findViewById(R.id.l2)
        l3 = findViewById(R.id.l3)
        l4 = findViewById(R.id.l4)
        l11 = findViewById(R.id.l11)
        l22 = findViewById(R.id.l22)
        l33 = findViewById(R.id.l33)
        l44 = findViewById(R.id.l44)
        face = ResourcesCompat.getFont(this, R.font.rounds)!!
        ivt = findViewById(R.id.ivt)
        levelTv = findViewById(R.id.allFilmsTv3)
        moneyTv = findViewById(R.id.moneyTv3)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setViews() {
        sound.setMinFrame(60)

        lettersLayout.setOnTouchListener(object : OnSwipeTouchListener(this) {
            override fun onSwipeRight() {
                prevFilm(null)
            }

            override fun onSwipeLeft() {
                nextFilm(null)
            }
        })

    }

    private var keyboardClick = View.OnClickListener { v ->
        if (answerPtr < guessButtons.size) {
            play(Click1)
            if (keyboardButtons[v.id - 100].text !== "") {
                guessButtons[answerPtr].text = keyboardButtons[v.id - 100].text
                keyboardButtons[v.id - 100].text = ""
                keyboardButtons[v.id - 100].visibility = View.INVISIBLE
                while (answerPtr < guessButtons.size && guessButtons[answerPtr].text !== "") {
                    answerPtr++
                }

                isPassed
            }
        }
    }
    private var answerClick = View.OnClickListener { v ->
        if (guessButtons[v.id].text !== "") {
            play(Click1)
            val temp = guessButtons[v.id].text.toString()
            guessButtons[v.id].text = ""
            answerPtr = 0
            while (answerPtr < guessButtons.size && guessButtons[answerPtr].text !== "") {
                answerPtr++
            }
            for (i in keyboardButtons.indices) {
                if (keyboardButtons[i].contentDescription.toString() == temp && keyboardButtons[i].text === "") {
                    keyboardButtons[i].text = keyboardButtons[i].contentDescription
                    keyboardButtons[i].visibility = View.VISIBLE
                    break
                }
            }
        }
    }

    private fun dpToPixels(n: Int): Int {
        val scale = this.resources.displayMetrics.density
        return (n * scale).toInt()
    }

    private fun rnd(min: Int, max: Int): Int {
        var max = max
        max -= min
        return (Math.random() * ++max).toInt() + min
    }

    fun nextFilm(view: View?) {
        val swipeAnim: ValueAnimator = ObjectAnimator.ofFloat(ivt, "translationX", OFFSET)
        val lettersLayoutAnim: ValueAnimator =
            ObjectAnimator.ofFloat(lettersLayout, "translationX", OFFSET)
        val keyboardLayoutAnim: ValueAnimator =
            ObjectAnimator.ofFloat(keyboardLayout, "translationX", OFFSET)
        swipeAnim.duration = (ANIM_DURATION / 2).toLong()
        lettersLayoutAnim.duration = (ANIM_DURATION / 2).toLong()
        keyboardLayoutAnim.duration = (ANIM_DURATION / 2).toLong()
        swipeAnim.start()
        lettersLayoutAnim.start()
        keyboardLayoutAnim.start()

        swipeAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {

                if (filmId + 1 < dataLoader.packs[packId].films.size) {
                    val i = Intent(this@ArcadeGameActivity, ArcadeGameActivity::class.java)
                    i.putExtra("filmId", filmId + 1)
                    i.putExtra("packId", packId)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    i.putExtra("isLastMain", false)
                    i.putExtra("isLastPrev", true)
                    startActivity(i)
                    overridePendingTransition(0, 0)
                    finish()
                } else {
                    val i = Intent(this@ArcadeGameActivity, ArcadeFilmsViewActivity::class.java)
                    i.putExtra("isLastMain", true)
                    i.putExtra("isLastPrev", false)
                    i.putExtra("packId", packId)
                    startActivity(i)
                    finish()
                }

            }
        })
    }

    fun prevFilm(view: View?) {
        val swipeAnim: ValueAnimator = ObjectAnimator.ofFloat(ivt, "translationX", -OFFSET)
        val lettersLayoutAnim: ValueAnimator =
            ObjectAnimator.ofFloat(lettersLayout, "translationX", -OFFSET)
        val keyboardLayoutAnim: ValueAnimator =
            ObjectAnimator.ofFloat(keyboardLayout, "translationX", -OFFSET)
        swipeAnim.duration = (ANIM_DURATION / 2).toLong()
        lettersLayoutAnim.duration = (ANIM_DURATION / 2).toLong()
        keyboardLayoutAnim.duration = (ANIM_DURATION / 2).toLong()
        swipeAnim.start()
        lettersLayoutAnim.start()
        keyboardLayoutAnim.start()
        swipeAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (filmId - 1 >= 0) {
                    val i = Intent(this@ArcadeGameActivity, ArcadeGameActivity::class.java)
                    i.putExtra("filmId", filmId - 1)
                    i.putExtra("packId", packId)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(i)
                    overridePendingTransition(0, 0)
                    finish()
                } else {
                    val i = Intent(this@ArcadeGameActivity, ArcadeFilmsViewActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        })
    }

    fun passLevel(view: View?) {
        //if (player!!.money >= 0) {
            if (!dataLoader.packs[packId].films[filmId].completed) {
                for (i in guessButtons.indices) {
                    guessButtons[i].text = guessButtons[i].contentDescription
                }
                k = 0
                for (j in guessButtons.indices) {
                    for (i in keyboardButtons.indices) {
                        if (keyboardButtons[i].text.toString() == guessButtons[k].text.toString()) {
                            keyboardButtons[i].text = ""
                            keyboardButtons[i].visibility = View.INVISIBLE
                            break
                        }
                    }
                    k++
                }

            }
            player!!.money -= 80
            moneyTv.text = player!!.money.toString()
            isPassed
        }
    //}

    fun showFirstLetter(view: View?) {
        //if (player!!.money >= 0) {
            play(Click1)
            if (k > guessButtons.size - 1) k = 0
            while (previewedChars.contains(guessButtons[k].text) && k < guessButtons.size) {
                k++
            }
            guessButtons[k].text = guessButtons[k].contentDescription
            guessButtons[k].isClickable = false
            for (i in keyboardButtons.indices) {
                if (keyboardButtons[i].text.toString() == guessButtons[k].text.toString()) {
                    keyboardButtons[i].text = ""
                    keyboardButtons[i].visibility = View.INVISIBLE
                    break
                }
            }
            k++
            answerPtr = k
            player!!.money -= 10
            moneyTv.text = player!!.money.toString()

            isPassed
        }
    //}

    fun removeOtherLetters(view: View?) {
        if (player!!.money >= 20) {
            for (j in keyboardButtons.indices) {
                if (keyboardButtons[j].alpha == 0.999f) {
                    keyboardButtons[j].text = ""
                    keyboardButtons[j].visibility = View.INVISIBLE
                }
            }
            player!!.money -= 20
            moneyTv.text = player!!.money.toString()
        }
    }

    private val isPassed: Boolean
        get() {
            val current = StringBuilder()
            for (i in guessButtons.indices) {
                current.append(guessButtons[i].text)
            }
            if (current.toString() == answer) {
                player!!.money += 30
                moneyTv.text = player!!.money.toString()
                dataLoader.packs[packId].films[filmId].completed = true
                film?.completed = true
                dataLoader.repo.updateFilm(film!!)
                dataLoader.repo.updatePlayer(player!!)
                fireworksAnim.playAnimation()
                fireworksAnim.setMaxFrame(40)
                fireworksAnim.speed = 2f
                play(Correct1)
                Handler().postDelayed({ nextFilm(null) }, 1000) //2600 def
                return true
            } else return false
        }

    private fun setEditable(isEditable: Boolean) {
        if (!isEditable) {
            for (i in guessButtons.indices) {
                guessButtons[i].isClickable = false
            }
            for (i in keyboardButtons.indices) {
                keyboardButtons[i].isClickable = false
            }
            passButton.isClickable = false
            oneLetterButton.isClickable = false
            deleteLettersButton.isClickable = false
        }
    }

    fun startShopActivity(view: View?) {
        val i = Intent(this, ShopActivity::class.java)
        startActivity(i)
        finish()
    }

    fun checkSound(view: View?) {
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
        sound.playAnimation()
    }
}