package com.viona.registrationapp.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import retrofit2.Retrofit

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisibleIfElseGone(condition: Boolean) =
    if (condition) this.setVisible() else this.setGone()

fun View.showSnackbar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionText: CharSequence? = null,
    actionCallback: (() -> Unit)? = null,
) {
    val snackbar = Snackbar.make(this, message, duration)

    if (actionText != null && actionCallback != null) {
        snackbar.setAction(actionText) {
            actionCallback.invoke()
        }
    }

    snackbar.show()
}

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
