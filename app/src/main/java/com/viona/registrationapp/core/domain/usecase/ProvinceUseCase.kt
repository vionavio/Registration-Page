package com.viona.registrationapp.core.domain.usecase

import com.viona.registrationapp.core.data.Resource
import com.viona.registrationapp.core.domain.model.Province
import kotlinx.coroutines.flow.Flow

interface ProvinceUseCase {
    fun getProvince(): Flow<Resource<List<Province>>>
}