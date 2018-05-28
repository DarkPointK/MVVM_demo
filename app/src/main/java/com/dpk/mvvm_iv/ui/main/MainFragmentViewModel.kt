package com.dpk.mvvm_iv.ui.main

import android.databinding.*
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.dpk.mvvm_iv.model.ApiClient
import com.dpk.mvvm_iv.model.NetBean
import com.dpk.mvvm_iv.model.SwitchSchedulers

class MainFragmentViewModel : BaseObservable() {
    val SCUCCSE = 1
    val FAIL = 0
    val DEF = 2

    var listener = ""
        @Bindable get
        set(value) {
            field = value
            if (value.isNotEmpty())
                key.set(true)
            notifyPropertyChanged(BR.listener)
        }
    var ispass = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.ispass)
        }
    var base64 = ObservableField<NetBean.PostInspection>()
    val key = ObservableBoolean(false)
    val loading = ObservableBoolean(false)
    val issuccess = ObservableInt(DEF)


    var getInspectionBean = ObservableField<NetBean.GetInspection>()
        @Bindable get
        set(value) {
            field = value
            loading.set(false)
            notifyPropertyChanged(BR.getInspectionBean)
        }

    fun inspection() {
        if (base64.get() != null) {
            ApiClient.inspection(base64.get()!!).compose(SwitchSchedulers.applySchedulers()).subscribe(
                    { getInspectionBean.set(it) },
                    { issuccess.set(FAIL) }
            )
        }
    }

    fun getIsOfficerVehicle(view: View) {
        loading.set(true)
        ApiClient.getIsOfficerVehicle(listener).compose(SwitchSchedulers.applySchedulers()).subscribe(
                {
                    loading.set(false)
                    if (it.isSuccessful) {
                        ispass = it.headers().get("isOfficerVehicle")?.toBoolean()!!
                        issuccess.set(SCUCCSE)
                    }
                },
                {
                    loading.set(false)
                    issuccess.set(FAIL)
                }
        )
    }
}