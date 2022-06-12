package com.bangkit.eduboxloginregister.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bangkit.eduboxloginregister.R

class BtnSendLink : AppCompatButton {
    private lateinit var enabledBg: Drawable
    private lateinit var disabledBg: Drawable
    private var txtColor: Int = 1

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = if (isEnabled) enabledBg else disabledBg

        setTextColor(ContextCompat.getColor(context, R.color.white))
        textSize = 16f
        gravity = Gravity.CENTER
        text = if (isEnabled) {
            context.getString(R.string.str_send_link)
        } else {
            isEnabled = false
            context.getString(R.string.str_send_link)
        }
    }

    private fun init() {
        txtColor = ContextCompat.getColor(context, R.color.white)
        enabledBg = ContextCompat.getDrawable(context, R.drawable.bg_btn_enabled) as Drawable
        disabledBg = ContextCompat.getDrawable(context, R.drawable.bg_btn_disabled) as Drawable
    }
}