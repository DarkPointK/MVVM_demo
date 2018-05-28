package com.dpk.mvvm_iv.utils

import android.databinding.BindingAdapter
import android.view.View
import org.jetbrains.annotations.NotNull

@BindingAdapter("visible")
fun View.visible(@NotNull visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}