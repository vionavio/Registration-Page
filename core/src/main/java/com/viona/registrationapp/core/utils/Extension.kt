package com.viona.registrationapp.core.utils

import retrofit2.Retrofit

inline fun retrofit(init: Retrofit.Builder.() -> Unit): Retrofit {
    val retrofit = Retrofit.Builder()
    retrofit.init()
    return retrofit.build()
}