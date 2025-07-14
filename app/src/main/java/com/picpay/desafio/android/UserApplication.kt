package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.DataModules
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class UserApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules(
                DataModules.dataModules
            )
        }
    }
}