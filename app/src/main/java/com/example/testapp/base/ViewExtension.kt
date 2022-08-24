package com.example.testapp.base

import android.view.View
import androidx.fragment.app.Fragment

fun View.setVisibility(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun Fragment.tag(): String = this::class.java.name