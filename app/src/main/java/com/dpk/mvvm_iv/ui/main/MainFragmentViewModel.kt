package com.dpk.mvvm_iv.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.dpk.mvvm_iv.model.ApiClient
import com.dpk.mvvm_iv.model.NetBean
import com.dpk.mvvm_iv.model.SwitchSchedulers

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    //输入的车牌号
    val listener = MutableLiveData<String>()

    //是否通过检验
    val ispass = MutableLiveData<Boolean>()

    //加载状态
    val loading = MutableLiveData<Boolean>()

    //请求检验的方式
    val key = MutableLiveData<Boolean>()

    //是否完成检验
    val issuccess = MutableLiveData<Boolean>().apply { value = true }

    var base64 = MutableLiveData<NetBean.PostInspection>()
        set(value) {
            field = value
            inspection()
        }

    var getInspectionBean = MutableLiveData<NetBean.GetInspection>()
        set(value) {
            field = value
            loading.value = false
        }

    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        listener.value = s.toString()
        if (s.toString().isNotEmpty())
            key.value = true
    }

    fun inspection() {
        if (base64.value != null) {
            ApiClient.inspection(base64.value!!).compose(SwitchSchedulers.applySchedulers()).subscribe(
                    { getInspectionBean.value = it },
                    { issuccess.value = false }
            )
        }
    }

    fun getIsOfficerVehicle(view: View) {
        loading.value = true
        ApiClient.getIsOfficerVehicle(listener.value!!).compose(SwitchSchedulers.applySchedulers()).subscribe(
                {
                    loading.value = false
                    if (it.isSuccessful) {
                        ispass.value = it.headers().get("isOfficerVehicle")?.toBoolean()
                        issuccess.value = true
                    }
                },
                {
                    loading.value = false
                    issuccess.value = true
                }
        )
    }
}