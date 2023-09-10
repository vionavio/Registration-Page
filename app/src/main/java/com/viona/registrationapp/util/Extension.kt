package com.viona.registrationapp.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar

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

fun EditText.addAfterTextChangedListener(afterTextChangedAction: (Editable?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            afterTextChangedAction(s)
        }
    })
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

