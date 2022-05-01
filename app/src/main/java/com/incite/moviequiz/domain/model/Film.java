package com.incite.moviequiz.domain.model;

import android.graphics.drawable.Drawable;

public class Film {
    int drawableID;

    boolean isPassed = false;

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public String getAnswer() {
        return answer;
    }

    String answer;

    public Film(String answer, int drawableID) {
        this.drawableID = drawableID;
        this.answer = answer;
    }
}
