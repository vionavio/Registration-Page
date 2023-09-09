package com.viona.registrationapp.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viona.registrationapp.core.domain.usecase.ProvinceUseCase
import com.viona.registrationapp.di.AppScope
import com.viona.registrationapp.ui.address.AddressViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(
    private val provinceUseCase: ProvinceUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AddressViewModel::class.java) -> {
                AddressViewModel(provinceUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}
