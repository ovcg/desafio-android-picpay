package com.picpay.desafio.android.common

import android.app.Application
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class UserApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules()
        }
    }
}