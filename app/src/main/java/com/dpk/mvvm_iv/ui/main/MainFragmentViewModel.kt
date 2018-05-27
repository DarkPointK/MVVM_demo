package com.dpk.mvvm_iv.ui.main

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.android.databinding.library.baseAdapters.BR
import com.dpk.mvvm_iv.model.ApiClient
import com.dpk.mvvm_iv.model.NetBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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

    var getInspectionBean = ObservableField<NetBean.GetInspection>()
        @Bindable get
        set(value) {
            field = value
            loading.set(false)
            issuccess.set(true)
            notifyPropertyChanged(BR.getInspectionBean)
        }

    fun inspection(base64: NetBean.PostInspection) {
        ApiClient.inspection(base64).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                { getInspectionBean.set(it) },
                { issuccess.set(false) }
        )
    }

    fun getIsOfficerVehicle() {
        ApiClient.getIsOfficerVehicle(listener).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                {
                    if (it.isSuccessful)
                        issuccess.set(java.lang.Boolean.parseBoolean(it.headers().get("isOfficerVehicle")))
                },
                { issuccess.set(false) }
        )
    }
}