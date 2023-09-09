package com.viona.registrationapp.di

import com.viona.registrationapp.core.domain.usecase.ProvinceUseCase
import com.viona.registrationapp.core.domain.usecase.ProvinceUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideProvinceUseCase(
        provinceUseCaseImpl: ProvinceUseCaseImpl,
    ): ProvinceUseCase
}
