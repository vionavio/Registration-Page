package com.viona.registrationapp.core.di

import com.viona.registrationapp.core.data.ProvinceRepositoryImpl
import com.viona.registrationapp.core.data.source.RemoteDataSource
import com.viona.registrationapp.core.data.source.network.ApiConfig
import com.viona.registrationapp.core.domain.repository.ProvinceRepository
import com.viona.registrationapp.core.domain.usecase.ProvinceUseCase
import com.viona.registrationapp.core.domain.usecase.ProvinceUseCaseImpl

object Injection {
    private fun provideRepository(): ProvinceRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.apiService)

        return ProvinceRepositoryImpl.getInstance(remoteDataSource)
    }

    fun provideProvinceUseCase(): ProvinceUseCase {
        val repository = provideRepository()
        return ProvinceUseCaseImpl(repository)
    }
}
