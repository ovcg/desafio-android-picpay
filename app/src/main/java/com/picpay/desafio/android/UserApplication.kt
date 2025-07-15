package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UserApplication)
            loadKoinModules(Modules.module)
        }
    }
}
