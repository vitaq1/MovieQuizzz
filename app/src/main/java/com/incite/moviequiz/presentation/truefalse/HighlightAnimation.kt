package com.incite.moviequiz.presentation.truefalse

import android.animation.Animator
import android.view.animation.Animation
import android.animation.TimeInterpolator
import android.widget.FrameLayout
import android.view.ViewGroup
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.ImageView

class HighlightAnimation(var view: View, var color: Int, duration: Long) : Animation() {
    var interpolator: TimeInterpolator
    private var duration: Long
    var listener: AnimationListener?
    fun animate() {
        val highlightFrame = FrameLayout(view.context)
        val layoutParams = ViewGroup.LayoutParams(
            view.width,
            view.height
        )
        val highlightView = ImageView(view.context)
        highlightView.setBackgroundColor(color)
        highlightView.alpha = 0.5f
        highlightView.visibility = View.VISIBLE
        val parentView = view.parent as ViewGroup
        val positionView = parentView.indexOfChild(view)
        parentView.addView(highlightFrame, positionView, layoutParams)
        highlightFrame.x = view.left.toFloat()
        highlightFrame.y = view.top.toFloat()
        parentView.removeView(view)
        highlightFrame.addView(view)
        highlightFrame.addView(highlightView)
        highlightView.animate().alpha(0f).setInterpolator(interpolator)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    highlightFrame.removeAllViews()
                    parentView.addView(view, positionView)
                    view.x = highlightFrame.left.toFloat()
                    view.y = highlightFrame.top.toFloat()
                    parentView.removeView(highlightFrame)
                    if (listener != null) {
                        listener!!.onAnimationEnd(
                            this@HighlightAnimation
                        )
                    }
                }
            })
    }

    fun setColor(color: Int): HighlightAnimation {
        this.color = color
        return this
    }

    override fun getInterpolator(): Interpolator {
        return interpolator as Interpolator
    }

    fun setInterpolator(interpolator: TimeInterpolator): HighlightAnimation {
        this.interpolator = interpolator
        return this
    }

    override fun getDuration(): Long {
        return duration
    }

    override fun setDuration(duration: Long) {
        this.duration = duration
    }

    fun setListener(listener: AnimationListener?): HighlightAnimation {
        this.listener = listener
        return this
    }

    companion object {
        const val DURATION_LONG = 300
    }

    init {
        interpolator = AccelerateDecelerateInterpolator()
        this.duration = duration
        listener = null
    }
}