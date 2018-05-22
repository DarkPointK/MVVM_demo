package com.dpk.mvvm_iv.ui.main

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField

class MainFragmentViewModel : BaseObservable() {
    var listener = ObservableField<String>("")
    var loading=ObservableBoolean(false)

}