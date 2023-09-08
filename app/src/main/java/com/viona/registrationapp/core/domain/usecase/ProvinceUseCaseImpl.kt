package com.viona.registrationapp.core.domain.usecase

import com.viona.registrationapp.core.domain.repository.ProvinceRepository

class ProvinceUseCaseImpl(private val provinceRepository: ProvinceRepository) : ProvinceUseCase {
    override fun getProvince() = provinceRepository.getProvince()
}
