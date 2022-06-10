package com.bangkit.edubox.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.bangkit.edubox.R
import com.google.android.material.textfield.TextInputEditText

class EditTextUsername : TextInputEditText, View.OnTouchListener {
    private lateinit var clearButton: Drawable

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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setBackgroundResource(R.drawable.border_text_input)

        setHint(R.string.txtUsername)
        setPadding(30, 30, 30, 30)
        textSize = 12f
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        clearButton = ContextCompat.getDrawable(context, R.drawable.ic_close) as Drawable

        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun afterTextChanged(e: Editable) {
                if (e.toString().isNotEmpty()) showClearButton() else hideClearButton()
                if (e.toString().isEmpty()) {
                    showError()
                }
            }
        })
    }

    private fun showError() {
        error = context.getString(R.string.usernameEmpty)
    }

    private fun showClearButton() {
        setButtonDrawable(endOfTheText = clearButton)
    }

    private fun hideClearButton() {
        setButtonDrawable()
    }

    private fun setButtonDrawable(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                clearButtonEnd = (clearButton.intrinsicWidth + paddingStart).toFloat()
                if (event.x < clearButtonEnd) isClearButtonClicked = true
            } else {
                clearButtonStart = (width - paddingEnd - clearButton.intrinsicWidth).toFloat()
                if (event.x > clearButtonStart) isClearButtonClicked = true
            }

            if (isClearButtonClicked) {
                return when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        clearButton =
                            ContextCompat.getDrawable(context, R.drawable.ic_close) as Drawable
                        showClearButton()
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        clearButton =
                            ContextCompat.getDrawable(context, R.drawable.ic_close) as Drawable
                        if (text != null) text?.clear()
                        hideClearButton()
                        true
                    }
                    else -> false
                }
            } else
                return false
        }
        return false
    }
}