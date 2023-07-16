package com.azalia.suitmediatestkm.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.azalia.suitmediatestkm.R

class CustomButton : AppCompatButton {

    private lateinit var enabledBackground: Drawable
    private lateinit var disabledBackground: Drawable
    private var txtColorEnable: Int = 0
    private var txtColorDisable: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = if (isEnabled) {
            setTextColor(txtColorEnable)
            enabledBackground
        } else {
            setTextColor(txtColorDisable)
            disabledBackground
        }

        textSize = 14f
        gravity = Gravity.CENTER
//        text = if (isEnabled) context.getString(R.string.submit) else context.getString(R.string.fill_first)
    }

    private fun init() {
        txtColorEnable = ContextCompat.getColor(context, android.R.color.background_light)
        txtColorDisable = ContextCompat.getColor(context, R.color.button)
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.button_round) as Drawable
        disabledBackground = ContextCompat.getDrawable(context, R.drawable.button_round_disable) as Drawable
    }
}