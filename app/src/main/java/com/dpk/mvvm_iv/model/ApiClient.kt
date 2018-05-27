package com.dpk.mvvm_iv.model

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient : ApiInterface {
    val instant = Retrofit.Builder()
            .baseUrl("")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    override fun inspection(body: NetBean.PostInspection): Observable<NetBean.GetInspection> {
        return instant.inspection(body)
    }

    override fun getIsOfficerVehicle(carPlate: String): Observable<Response<Void>> {
        return instant.getIsOfficerVehicle(carPlate)
    }
}