package com.viona.registrationapp.core.data.source.response

import com.google.gson.annotations.SerializedName

data class DataItemResponseData(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: String? = null,
)
