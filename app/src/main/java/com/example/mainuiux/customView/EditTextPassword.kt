package com.example.mainuiux.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.mainuiux.R
import com.google.android.material.textfield.TextInputEditText

class EditTextPassword : TextInputEditText, View.OnTouchListener {

    private lateinit var passToggle: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        showEyeButton()
        setBackgroundResource(R.drawable.border_text_input)
        setPadding(30, 30, 30, 30)
        textSize = 12f
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        passToggle = ContextCompat.getDrawable(context, R.drawable.ic_hidden_pass) as Drawable

        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //do nothing
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().length <= 8) showError()
            }
        })
    }

    private fun showError() {
        error = context.getString(R.string.pass_6_char)
    }

    private fun showEyeButton() {
        setButtonDrawable(endOfTheText = passToggle)
    }

    private fun hideEyeButton() {
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
            val eyeButtonStart: Float
            val eyeButtonEnd: Float
            var isEyeButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                eyeButtonEnd = (passToggle.intrinsicWidth + paddingStart).toFloat()
                if (event.x < eyeButtonEnd) isEyeButtonClicked = true
            } else {
                eyeButtonStart = (width - paddingEnd - passToggle.intrinsicWidth).toFloat()
                if (event.x > eyeButtonStart) isEyeButtonClicked = true
            }

            if (isEyeButtonClicked) {
                return when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        hideEyeButton()
                        if (transformationMethod.equals(HideReturnsTransformationMethod.getInstance())) {
                            transformationMethod = PasswordTransformationMethod.getInstance()
                            passToggle = ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_hidden_pass
                            ) as Drawable
                            showEyeButton()
                        } else {
                            transformationMethod = HideReturnsTransformationMethod.getInstance()
                            passToggle = ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_show_pass
                            ) as Drawable
                            showEyeButton()
                        }
                        true
                    }
                    else -> false
                }
            } else return false
        }
        return false
    }
}