package com.viona.registrationapp.core.domain.usecase

import com.viona.registrationapp.core.domain.repository.ProvinceRepository
import javax.inject.Inject

class ProvinceUseCaseImpl @Inject constructor(
    private val provinceRepository: ProvinceRepository,
) : ProvinceUseCase {
    override fun getProvince() = provinceRepository.getProvince()
}
