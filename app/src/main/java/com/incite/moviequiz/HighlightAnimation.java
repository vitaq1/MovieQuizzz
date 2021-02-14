package com.incite.moviequiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * This animation makes use of a translucent box to overlay the view before
 * animating its alpha property to mimic the highlighting of the view.
 *
 * @author SiYao
 *
 */
public class HighlightAnimation extends Animation {

    int color;
    TimeInterpolator interpolator;
    long duration;
    AnimationListener listener;
    View view;
    final static int DURATION_LONG = 300;


    public HighlightAnimation(View view, int color, long duration) {
        this.view = view;
        this.color = color;
        interpolator = new AccelerateDecelerateInterpolator();
        this.duration = duration;
        listener = null;
    }

    public void animate() {
        final FrameLayout highlightFrame = new FrameLayout(view.getContext());
        LayoutParams layoutParams = new LayoutParams(view.getWidth(),
                view.getHeight());
        ImageView highlightView = new ImageView(view.getContext());
        highlightView.setBackgroundColor(color);
        highlightView.setAlpha(0.5f);
        highlightView.setVisibility(View.VISIBLE);

        final ViewGroup parentView = (ViewGroup) view.getParent();
        final int positionView = parentView.indexOfChild(view);
        parentView.addView(highlightFrame, positionView, layoutParams);
        highlightFrame.setX(view.getLeft());
        highlightFrame.setY(view.getTop());
        parentView.removeView(view);
        highlightFrame.addView(view);
        highlightFrame.addView(highlightView);

        highlightView.animate().alpha(0).setInterpolator(interpolator)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        highlightFrame.removeAllViews();
                        parentView.addView(view, positionView);
                        view.setX(highlightFrame.getLeft());
                        view.setY(highlightFrame.getTop());
                        parentView.removeView(highlightFrame);
                        if (getListener() != null) {
                            getListener().onAnimationEnd(
                                    HighlightAnimation.this);
                        }
                    }
                });
    }

    /**
     * @return The color of the highlight.
     * @see android.graphics.Color
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color
     *            The color of the highlight to set.
     * @return This object, allowing calls to methods in this class to be
     *         chained.
     * @see android.graphics.Color
     */
    public HighlightAnimation setColor(int color) {
        this.color = color;
        return this;
    }

    /**
     * @return The interpolator of the entire animation.
     */
    public Interpolator getInterpolator() {
        return (Interpolator) interpolator;
    }

    /**
     * @param interpolator
     *            The interpolator of the entire animation to set.
     */
    public HighlightAnimation setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    /**
     * @return The duration of the entire animation.
     */
    public long getDuration() {
        return duration;
    }

    /**
     * @param duration
     *            The duration of the entire animation to set.
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * @return The listener for the end of the animation.
     */
    public AnimationListener getListener() {
        return listener;
    }

    /**
     * @param listener
     *            The listener to set for the end of the animation.
     * @return This object, allowing calls to methods in this class to be
     *         chained.
     */
    public HighlightAnimation setListener(AnimationListener listener) {
        this.listener = listener;
        return this;
    }

}
