package com.sirius.test_app.recycler.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

internal fun ViewGroup.inflate(@LayoutRes layoutId: Int): View {
    val layoutInflater = LayoutInflater.from(context)
    return layoutInflater.inflate(layoutId, this, false)
}
