package com.viona.registrationapp.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import retrofit2.Retrofit

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisibleIfElseGone(condition: Boolean) =
    if (condition) this.setVisible() else this.setGone()

// Live Data
fun <T> LiveData<T>.observableData(
    owner: LifecycleOwner,
    action: (T) -> Unit,
) {
    this.observe(owner) { data ->
        action.invoke(data)
    }
}

// retrofit builder
inline fun retrofit(init: Retrofit.Builder.() -> Unit): Retrofit {
    val retrofit = Retrofit.Builder()
    retrofit.init()
    return retrofit.build()
}
