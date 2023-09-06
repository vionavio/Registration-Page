package com.viona.registrationapp.util

import android.view.View

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisibleIfElseGone(condition: Boolean) =
    if (condition) this.setVisible() else this.setGone()
