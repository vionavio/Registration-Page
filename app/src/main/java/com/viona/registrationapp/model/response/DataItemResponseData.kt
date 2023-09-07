package com.viona.registrationapp.model.response

import com.google.gson.annotations.SerializedName

data class DataItemResponseData(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: String? = null,
)