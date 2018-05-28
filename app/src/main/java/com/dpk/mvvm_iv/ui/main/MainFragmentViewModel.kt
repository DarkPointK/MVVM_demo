package com.dpk.mvvm_iv.ui.main

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.dpk.mvvm_iv.model.ApiClient
import com.dpk.mvvm_iv.model.NetBean
import com.dpk.mvvm_iv.model.SwitchSchedulers

class MainFragmentViewModel : BaseObservable() {

    var listener = ""
        @Bindable get
        set(value) {
            field = value
            if (value.isNotEmpty())
                key.set(true)
            notifyPropertyChanged(BR.listener)
        }
    var base64 = ObservableField<NetBean.PostInspection>()
    val key = ObservableBoolean(false)
    val loading = ObservableBoolean(false)
    val issuccess = ObservableBoolean(true)

    var getInspectionBean = ObservableField<NetBean.GetInspection>()
        @Bindable get
        set(value) {
            field = value
            loading.set(false)
            issuccess.set(true)
            notifyPropertyChanged(BR.getInspectionBean)
        }

    fun inspection() {
        if (base64.get() != null) {
            ApiClient.inspection(base64.get()!!).compose(SwitchSchedulers.applySchedulers()).subscribe(
                    { getInspectionBean.set(it) },
                    { issuccess.set(false) }
            )
        }
    }

    fun getIsOfficerVehicle(view: View) {
        loading.set(true)
        ApiClient.getIsOfficerVehicle(listener).compose(SwitchSchedulers.applySchedulers()).subscribe(
                {
                    loading.set(false)
                    if (it.isSuccessful)
                        issuccess.set(it.headers().get("isOfficerVehicle")?.toBoolean() ?: false)
                },
                {
                    loading.set(false)
                    issuccess.set(false)
                }
        )
    }
}