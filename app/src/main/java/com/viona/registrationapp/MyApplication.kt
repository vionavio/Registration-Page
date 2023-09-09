package com.viona.registrationapp

import android.app.Application
import com.viona.registrationapp.core.di.CoreComponent
import com.viona.registrationapp.core.di.DaggerCoreComponent
import com.viona.registrationapp.di.AppComponent
import com.viona.registrationapp.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}
