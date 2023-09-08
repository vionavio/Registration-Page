package com.viona.registrationapp.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viona.registrationapp.core.di.Injection
import com.viona.registrationapp.core.domain.usecase.ProvinceUseCase
import com.viona.registrationapp.ui.address.AddressViewModel

class ViewModelFactory private constructor(
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

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideProvinceUseCase(),
                )
            }
    }
}
