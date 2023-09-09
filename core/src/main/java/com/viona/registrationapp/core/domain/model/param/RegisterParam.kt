package com.viona.registrationapp.core.domain.model.param

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterParam(
    var nationalId: String? = "",
    var fullname: String? = "",
    var bankAccountNo: String? = "",
    var education: String? = "",
    var dob: String? = "",
    var domicile: String? = "",
    var housingType: String? = "",
    var houseNo: String? = "",
    var province: String? = "",
) : Parcelable
