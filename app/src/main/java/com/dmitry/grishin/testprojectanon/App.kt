package com.dmitry.grishin.testprojectanon

import android.app.Application
import com.dmitry.grishin.testprojectanon.common.di.*
import com.dmitry.grishin.testprojectanon.common.di.Scopes.APP_SCOPE
import com.dmitry.grishin.testprojectanon.data.network.repositories.IPostRepository
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator
import javax.inject.Inject

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initToothpick()
    }

    private fun initToothpick() {
        Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        Toothpick.openScope(APP_SCOPE).let {
            it.installModules(AppModule(this), DataModule, DomainModule, NetworkModule)
            Toothpick.inject(this@App, it)
        }
    }

}