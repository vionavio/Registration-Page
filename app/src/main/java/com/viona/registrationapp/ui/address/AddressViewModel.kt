package com.viona.registrationapp.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viona.registrationapp.core.data.Resource
import com.viona.registrationapp.core.domain.model.Province
import com.viona.registrationapp.core.domain.model.param.RegisterParam
import com.viona.registrationapp.core.domain.usecase.ProvinceUseCase
import kotlinx.coroutines.launch

class AddressViewModel(private val provinceUseCase: ProvinceUseCase) : ViewModel() {

    private val _provinceData: MutableLiveData<List<Province>> = MutableLiveData()
    val provinceData: LiveData<List<Province>> get() = _provinceData

    private var _dataParam = RegisterParam()
    val dataParam: RegisterParam get() = _dataParam
    fun getProvinceData() = viewModelScope.launch {
        provinceUseCase.getProvince().collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    _provinceData.postValue(resource.data.orEmpty())
                }

                else -> {}
            }
        }
    }

    fun setPersonalData(
        param: RegisterParam,
    ) {
        _dataParam = param
    }
}
