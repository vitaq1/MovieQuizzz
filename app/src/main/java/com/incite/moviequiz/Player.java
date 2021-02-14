package com.incite.moviequiz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.incite.moviequiz.data.Data;

import java.util.ArrayList;

public class Player {
    private static final String SAVED = "";
    private static final String MONEY = "";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor ed;

    static int currentPackId;
    static int currentFilmId;
    static boolean isLastMain = true;
    static boolean isLastPrev = true;

    static int money;
    static int guessRecord;
    private static String levelsData;

    ArrayList<String> filmProgressData = new ArrayList<>();


    static void addMoney(int a) {
        money += a;
    }


    static void saveProgress(Activity activity) {
        String temp = "";

        for (int i = 0; i < Data.getPacks().size(); i++) {
            for (int j = 0; j < Data.getPacks().get(0).size(); j++) {
                if (Data.getPacks().get(i).getFilms().get(j).isPassed()) {
                    temp += "1";
                } else temp += "0";
                System.out.println(temp);
            }
        }
        System.out.println(temp);
        sp = activity.getPreferences(Context.MODE_PRIVATE);
        ed = sp.edit();
        ed.putString(SAVED, temp);
        ed.putInt(MONEY,Player.money);
        ed.apply();
    }

    static void loadProgress(Activity activity) {
        sp = activity.getPreferences(Context.MODE_PRIVATE);
        levelsData = sp.getString(SAVED, "");
        money = sp.getInt(MONEY,0);
        System.out.println(levelsData);
    }

    static void setLevelPassed() {
        Data.getPacks().get(currentPackId).getFilms().get(currentFilmId).setPassed(true);
    }

}
