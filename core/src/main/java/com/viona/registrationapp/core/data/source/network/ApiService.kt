package com.viona.registrationapp.core.data.source.network

import com.viona.registrationapp.core.data.source.response.ProvinceResponseData
import retrofit2.http.GET

interface ApiService {
    @GET("regional/provinsi")
    suspend fun getProvince(): ProvinceResponseData
}
