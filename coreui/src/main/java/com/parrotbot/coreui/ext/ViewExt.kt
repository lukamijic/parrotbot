package com.parrotbot.coreui.ext

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

private const val COMMON_ANIMATION_DURATION = 300L

fun View.select(isSelected: Boolean = true) {
    this.isSelected = isSelected
}

fun View.deselect() = select(false)

fun View.show(shouldShow: Boolean = true) {
    visibility = if (shouldShow) View.VISIBLE else View.GONE
}

fun View.hide() = show(false)

fun View.enable(isEnabled: Boolean = true) {
    this.isEnabled = isEnabled
}

fun View.disable() = enable(isEnabled = false)

fun View.fade(show: Boolean, withStartDelay: Long = 0, duration: Long = COMMON_ANIMATION_DURATION) {
    if (show) {
        fadeIn(withStartDelay, duration)
    } else {
        fadeOut(withStartDelay, duration)
    }
}

fun View.fadeIn(withStartDelay: Long = 0, duration: Long = COMMON_ANIMATION_DURATION) {
    animate()
        .setDuration(duration)
        .setStartDelay(withStartDelay)
        .withStartAction { visibility = View.VISIBLE }
        .alpha(1f)
}

fun View.fadeOut(withStartDelay: Long = 0, duration: Long = COMMON_ANIMATION_DURATION) {
    animate()
        .setDuration(duration)
        .setStartDelay(withStartDelay)
        .alpha(0f)
        .withEndAction { visibility = View.GONE }
}

fun View.showKeyboard() {
    requestFocus()
    (context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .toggleSoftInputFromWindow(windowToken, InputMethodManager.SHOW_FORCED, 0)
}

fun View.hideKeyboard() {
    clearFocus()
    (context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}

fun View.onClick(action: () -> Unit) {
    setOnClickListener { action() }
}
