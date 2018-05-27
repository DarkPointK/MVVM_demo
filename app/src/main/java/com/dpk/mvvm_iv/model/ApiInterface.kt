package com.dpk.mvvm_iv.model

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HEAD
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @POST("lp")
    fun inspection(
            @Body body: NetBean.PostInspection
    ): Observable<NetBean.GetInspection>

    @HEAD("officer/officers/isOfficerVehicle")
    fun getIsOfficerVehicle(
            @Query("carPlate") carPlate: String
    ): Observable<Response<Void>>
}