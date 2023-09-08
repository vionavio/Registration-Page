package com.viona.registrationapp.core.data.source.response

import com.google.gson.annotations.SerializedName

data class ProvinceResponseData(

    @SerializedName("data")
    val data: List<DataItemResponseData>? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("status")
    val status: String? = null,
)


