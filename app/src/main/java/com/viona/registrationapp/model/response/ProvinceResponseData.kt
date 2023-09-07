package com.viona.registrationapp.model.response

import com.google.gson.annotations.SerializedName

data class ProvinceResponseData(

    @SerializedName("data")
    val data: List<DataItemResponseData?>? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("status")
    val status: String? = null,
)


