package com.viona.registrationapp.core.di

import com.viona.registrationapp.core.domain.repository.ProvinceRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class],
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(): CoreComponent
    }

    fun provideRepository(): ProvinceRepository
}
