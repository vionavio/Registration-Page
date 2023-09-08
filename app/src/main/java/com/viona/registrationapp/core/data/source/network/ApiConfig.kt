package com.viona.registrationapp.core.data.source.network

import com.viona.registrationapp.BuildConfig
import com.viona.registrationapp.util.retrofit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val authInterceptor = Interceptor { chain ->
        val request = chain.request()
        val requestApiKey = request.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.api_key)
            .build()
        val newRequest = request.newBuilder()
            .url(requestApiKey)
            .build()
        chain.proceed(newRequest)
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build()

    private val retrofit = retrofit {
        baseUrl("https://api.goapi.id/v1/")
        addConverterFactory(GsonConverterFactory.create())
        client(client)
        build()
    }

    val apiService by lazy {
        val create: ApiService = retrofit.create(ApiService::class.java)
        create
    }
}
