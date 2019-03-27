package com.example.gustavohenrique.customcomponents.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.example.gustavohenrique.customcomponents.R

// JVM OverLoads call all the constructors of the view and chain all the calls.
class Onboarding @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    // Variables

    private var pageIndicatorRadius: Float = 0f
    private var pageIndicatorStrokeWidth: Float = 0f
    private var pageIndicatorStrokeColor: Int = 0
    private var pageIndicatorColor: Int = 0
    private var pageIndicatorSelectedColor: Int = 0

    //Background colors
    private var pageBackgroundColor: Int = 0
    private val paint = Paint()
    private val backgroundRect = Rect(0,0,0,0)


    init {
        // The way to generate an id for this reusable component, like XML -> android:id=˜@+id/˜
        id = generateViewId()
        initialize(attrs)
    }

    // Canvas is the draw area of component like UIView
    override fun onDraw(canvas: Canvas) {
        // Background
        drawBackground(canvas)
        drawPageIndicator(canvas)
    }


    private fun initialize(attrs: AttributeSet?) {


        // The way to get the Attrs styleable class created in resources,
        // but we have to recover using the Android Context
        // This call lock the attrs styleable file to be readed
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Onboarding)


        pageIndicatorRadius = typedArray.getFloat(R.styleable.Onboarding_pageIndicatorRadius, 30f)
        pageIndicatorStrokeWidth = typedArray.getFloat(R.styleable.Onboarding_pageIndicatorStrokeWidth, 10f)
        pageIndicatorStrokeColor = typedArray.getColor(R.styleable.Onboarding_pageIndicatorStrokeColor, Color.BLACK)
        pageIndicatorColor = typedArray.getColor(R.styleable.Onboarding_pageIndicatorColor, Color.GRAY)
        pageIndicatorSelectedColor = typedArray.getColor(R.styleable.Onboarding_pageIndicatorSelectedColor, Color.WHITE)
        pageBackgroundColor = typedArray.getColor(R.styleable.Onboarding_pageBackgroundColor, Color.LTGRAY)

        // When you call recycle you will release the attrs styleable file to be read again
        typedArray.recycle()
    }

    private fun drawPageIndicator(canvas: Canvas) {
        val bottomPos = measuredHeight - pageIndicatorRadius - dpToPx(5f)
        val leftPos = measuredWidth / 2f - pageIndicatorRadius /2f

        paint.color = pageIndicatorStrokeColor
        canvas.drawCircle(leftPos, bottomPos,pageIndicatorRadius,paint)

        paint.color = pageIndicatorColor
        canvas.drawCircle(leftPos,bottomPos, pageIndicatorRadius,paint)
    }

    private fun drawBackground(canvas: Canvas) {
        paint.color = pageBackgroundColor
        paint.style = Paint.Style.FILL
        backgroundRect.set(0,0,measuredWidth,measuredHeight)
        canvas.drawRect(backgroundRect,paint)
    }

    private fun dpToPx(value: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)

}