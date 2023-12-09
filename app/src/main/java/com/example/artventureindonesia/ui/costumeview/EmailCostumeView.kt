package com.example.artventureindonesia.ui.costumeview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.artventureindonesia.R

class EmailCostumeView: AppCompatEditText {

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

    private fun init(){
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+".toRegex())) {
                    this@EmailCostumeView.error = context.getString(R.string.wrong_email)
                } else {
                    error = null
                }
            }
            override fun afterTextChanged(s: Editable) {

            }
        })
    }
}