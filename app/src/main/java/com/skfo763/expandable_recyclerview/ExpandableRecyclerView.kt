package com.skfo763.expandable_recyclerview

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView

class ExpandableRecyclerView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    RecyclerView(context, attrs, defStyle) {

    /**
     * value that related with min/max line of the recyclerview.
     * it would be automatically set at first draw time with adapter
     */
    private var minLine = 1
    private var maxLine = 0
    private var minHeight = 0
    private var maxHeight = 0


    /**
     * value related with expand, fold animation of the recyclerview.
     */
    private var isAnimationRunning = false
    private var expandAnimator: ValueAnimator? = null
    private var collapseAnimator: ValueAnimator? = null

    var animationDuration: Long = 350
    var animationInterpolator = DecelerateInterpolator()
    var onAnimationStart: (() -> Unit) = { }
    var onAnimationEnd: (() -> Unit) = { }
    var onAnimationRepeat: (() -> Unit) = { }
    var onAnimationCancel: (() -> Unit) = { }


    // variable that indicates the states of recyclerview -> expanded / collapsed
    private var isExpanded = false


    // animation that changes the actual height of the recyclerview
    private val updateListener = ValueAnimator.AnimatorUpdateListener {
        layoutParams.height = it.animatedValue as Int
        requestLayout()
    }


    // get typedArray attributes from xml file
    init {
        this.isNestedScrollingEnabled = false
        val typedArray = context.theme.obtainStyledAttributes(attrs,
            R.styleable.ExpandableRecyclerView, 0, 0)

        try {
            minLine = typedArray.getInteger(R.styleable.ExpandableRecyclerView_minLine, 1)
            maxLine = typedArray.getInteger(R.styleable.ExpandableRecyclerView_maxLine, 0)
            isExpanded = typedArray.getBoolean(R.styleable.ExpandableRecyclerView_isExpanded, false)
        } finally {
            typedArray.recycle()
        }
    }

    fun expandView() {
        expandAnimator?.start()
    }

    fun collapseView() {
        collapseAnimator?.start()
    }

    fun getIsExpanded() = isExpanded

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        if((expandAnimator == null || collapseAnimator == null)
            && childCount > 0 && layoutManager != null) {

            val itemHeight = layoutManager!!.getDecoratedMeasuredHeight(getChildAt(0))
            minHeight = minLine * itemHeight
            maxHeight = if(maxLine == 0) { measuredHeight } else { maxLine * itemHeight }

            if(minHeight != 0) {
                expandAnimator = setExpandOrCollapseAnimation(minHeight, maxHeight)
                collapseAnimator = setExpandOrCollapseAnimation(maxHeight, minHeight)
            }
        }

        if(isExpanded || isAnimationRunning || minHeight == 0) {
            super.onMeasure(widthSpec, heightSpec)
        } else {
            setMeasuredDimension(measuredWidth, minHeight)
        }
    }

    private fun setExpandOrCollapseAnimation(start: Int, end: Int): ValueAnimator {
        return ValueAnimator.ofInt(start, end).also {
            it.duration = animationDuration
            it.interpolator = animationInterpolator
            it.addUpdateListener(updateListener)
            it.addListener(object: Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) { onAnimationRepeat.invoke() }

                override fun onAnimationEnd(animation: Animator?) {
                    isExpanded = !isExpanded
                    onAnimationEnd.invoke()
                    isAnimationRunning = false
                }

                override fun onAnimationCancel(animation: Animator?) { onAnimationCancel.invoke() }

                override fun onAnimationStart(animation: Animator?) {
                    onAnimationStart.invoke()
                    isAnimationRunning = true
                }
            })
        }
    }
}