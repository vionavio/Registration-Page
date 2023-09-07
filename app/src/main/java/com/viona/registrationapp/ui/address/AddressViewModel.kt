package com.viona.registrationapp.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viona.registrationapp.model.response.ProvinceResponseData
import com.viona.registrationapp.service.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressViewModel : ViewModel() {
    private val _province: MutableLiveData<ProvinceResponseData> = MutableLiveData()
    val province: LiveData<ProvinceResponseData> = _province

    fun getProvinceData() {
        NetworkClient.apiService.getProvince().enqueue(
            object : Callback<ProvinceResponseData> {
                override fun onResponse(
                    call: Call<ProvinceResponseData>,
                    response: Response<ProvinceResponseData>,
                ) {
                    if (response.isSuccessful) {
                        val detailData = response.body()
                        _province.value = detailData ?: ProvinceResponseData()
                    }
                }

                override fun onFailure(call: Call<ProvinceResponseData>, t: Throwable) {
                    _province.value = ProvinceResponseData()
                }
            },
        )
    }
}
