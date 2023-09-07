package com.viona.registrationapp.service

import com.viona.registrationapp.model.response.ProvinceResponseData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("regional/provinsi")
    fun getProvince(): Call<ProvinceResponseData>
}
