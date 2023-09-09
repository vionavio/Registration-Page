package com.viona.registrationapp.di

import com.viona.registrationapp.core.di.CoreComponent
import com.viona.registrationapp.ui.address.AddressFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class],
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: AddressFragment)
}
