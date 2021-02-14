package com.incite.moviequiz;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundManager {

    private static SoundPool soundPool;

    static boolean isOn = true;

    private static int loadedNumControl = 0;
    private static int totalSoundNum = 7;
    static boolean isLoaded = false;
    static int tickStreamId;

    static int Click1;
    static int Click2;
    static int Correct1;
    static int Correct2;
    static int Tick;
    static int Error;
    static int Bubble;


    public static void init(Context context) {
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                loadedNumControl++;
            }
        });

        Click1 = soundPool.load(context, R.raw.click1, 1);
        Click2 = soundPool.load(context, R.raw.click2, 1);
        Correct1 = soundPool.load(context, R.raw.correct1, 1);
        Correct2 = soundPool.load(context, R.raw.correct2, 1);
        Tick = soundPool.load(context, R.raw.tick, 1);
        Error = soundPool.load(context, R.raw.error, 1);
        Bubble = soundPool.load(context, R.raw.bubble, 1);

    }

    public static void play(final int i) {
        if (loadedNumControl == totalSoundNum) {
            if (isOn) {
                if (i == Tick) {
                    tickStreamId = soundPool.play(i, 1, 1, 1, -1, 1);
                }
                soundPool.play(i, 1, 1, 1, 0, 1);
            }
        }
    }

    public static void stop(final int i) {
        soundPool.stop(i);
        soundPool.stop(tickStreamId);
    }


}
