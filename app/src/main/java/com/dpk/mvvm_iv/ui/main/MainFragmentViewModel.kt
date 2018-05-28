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

    //输入的车牌号
    var listener = ""
        @Bindable get
        set(value) {
            field = value
            if (value.isNotEmpty())
                key.set(true)
            notifyPropertyChanged(BR.listener)
        }

    //是否通过检验
    var ispass = ObservableBoolean()

    //加载状态
    var loading = ObservableBoolean()

    //请求检验的方式
    val key = ObservableBoolean(false)

    //是否完成检验
    val issuccess = object : ObservableBoolean(true) {
        override fun set(value: Boolean) {
            super.set(value)
            if (get() == value)
                notifyChange()
        }
    }

    var base64 = ObservableField<NetBean.PostInspection>()
        @Bindable get
        set(value) {
            field = value
            inspection()
        }

    var getInspectionBean = ObservableField<NetBean.GetInspection>()
        @Bindable get
        set(value) {
            field = value
            loading.set(false)
            notifyPropertyChanged(BR.getInspectionBean)
        }

    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        listener = s.toString()
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
                    if (it.isSuccessful) {
                        ispass.set(it.headers().get("isOfficerVehicle")?.toBoolean()!!)
                        issuccess.set(true)
                    }
                },
                {
                    loading.set(false)
                    issuccess.set(true)
                }
        )
    }
}