package com.incite.moviequiz.util

import android.content.Context
import android.media.SoundPool
import android.media.AudioManager
import com.incite.moviequiz.R

object SoundManager {

    private lateinit var soundPool: SoundPool
    var isOn = true
    private var loadedNumControl = 0
    private const val totalSoundNum = 7
    var isLoaded = false
    var tickStreamId = 0
    var Click1 = 0
    var Click2 = 0
    var Correct1 = 0
    var Correct2 = 0
    var Tick = 0
    var Error = 0
    var Bubble = 0

    fun init(context: Context?) {
        soundPool = SoundPool(5, AudioManager.STREAM_MUSIC, 0)
        soundPool.setOnLoadCompleteListener { soundPool, i, i1 -> loadedNumControl++ }
        Click1 = soundPool.load(context, R.raw.click1, 1)
        Click2 = soundPool.load(context, R.raw.click2, 1)
        Correct1 = soundPool.load(context, R.raw.correct1, 1)
        Correct2 = soundPool.load(context, R.raw.correct2, 1)
        Tick = soundPool.load(context, R.raw.tick, 1)
        Error = soundPool.load(context, R.raw.error, 1)
        Bubble = soundPool.load(context, R.raw.bubble, 1)
    }

    fun play(i: Int) {
        if (loadedNumControl == totalSoundNum) {
            if (isOn) {
                if (i == Tick) {
                    tickStreamId = soundPool.play(i, 1f, 1f, 1, -1, 1f)
                }
                soundPool.play(i, 1f, 1f, 1, 0, 1f)
            }
        }
    }

    fun stop(i: Int) {
        soundPool.stop(i)
        soundPool.stop(tickStreamId)
    }
}