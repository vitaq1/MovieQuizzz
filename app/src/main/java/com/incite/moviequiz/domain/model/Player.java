package com.incite.moviequiz.domain.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Player {
    private static final String SAVED = "";
    private static final String MONEY = "";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor ed;

    public static int currentPackId;
    public static int currentFilmId;
    public static boolean isLastMain = true;
    public static boolean isLastPrev = true;

    public static int money;
    public static int guessRecord;
    private static String levelsData;

    ArrayList<String> filmProgressData = new ArrayList<>();


    public static void addMoney(int a) {
        money += a;
    }


    public static void saveProgress(Activity activity) {
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

    public static void loadProgress(Activity activity) {
        sp = activity.getPreferences(Context.MODE_PRIVATE);
        levelsData = sp.getString(SAVED, "");
        money = sp.getInt(MONEY,0);
        System.out.println(levelsData);
    }

    public static void setLevelPassed() {
        Data.getPacks().get(currentPackId).getFilms().get(currentFilmId).setPassed(true);
    }

}
