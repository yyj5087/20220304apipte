package com.nepplus.a20220304apipte

import androidx.appcompat.app.AppCompatActivity

abstract class BasicActivity : AppCompatActivity() {

    val mContext = this
    abstract fun setupEvents()
    abstract fun setValues()
}