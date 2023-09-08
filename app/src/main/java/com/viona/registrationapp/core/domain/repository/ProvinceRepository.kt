package com.viona.registrationapp.core.domain.repository

import com.viona.registrationapp.core.data.Resource
import com.viona.registrationapp.core.domain.model.Province
import kotlinx.coroutines.flow.Flow

interface ProvinceRepository {
    fun getProvince(): Flow<Resource<List<Province>>>
}
