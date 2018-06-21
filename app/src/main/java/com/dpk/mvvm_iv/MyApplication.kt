package com.dpk.mvvm_iv

import android.app.Application

class MyApplication : Application() {
    companion object {
        @JvmStatic lateinit var inst: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        inst = this
    }

}