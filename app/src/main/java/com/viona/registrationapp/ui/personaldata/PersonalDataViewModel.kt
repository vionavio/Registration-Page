package com.viona.registrationapp.ui.personaldata

import androidx.lifecycle.ViewModel
import com.viona.registrationapp.core.domain.model.param.RegisterParam

class PersonalDataViewModel : ViewModel() {

    private var _dataParam = RegisterParam()
    val dataParam: RegisterParam get() = _dataParam

    fun setPersonalData(
        param: RegisterParam,
    ) {
        _dataParam = param
    }
}
