package com.viona.registrationapp.core.di

import com.viona.registrationapp.core.data.ProvinceRepositoryImpl
import com.viona.registrationapp.core.domain.repository.ProvinceRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(provinceRepositoryImpl: ProvinceRepositoryImpl):
        ProvinceRepository
}
