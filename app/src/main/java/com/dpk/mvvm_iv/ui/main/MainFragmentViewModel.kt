package com.dpk.mvvm_iv.ui.main

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableBoolean
import com.android.databinding.library.baseAdapters.BR

class MainFragmentViewModel : BaseObservable() {

    var listener = ""
        @Bindable get
        set(value) {
            field = value
            if (value.isNotEmpty())
                key.set(true)
            notifyPropertyChanged(BR.listener)
        }
    val key = ObservableBoolean(false)
    val loading = ObservableBoolean(false)
    val issuccess = ObservableBoolean(false)
}