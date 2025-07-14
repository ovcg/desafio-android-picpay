package com.picpay.desafio.android.data.common.di

import com.picpay.desafio.android.data.common.db.AppDatabase
import com.picpay.desafio.android.data.common.db.AppDatabaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {

    val module = module {
        single { AppDatabaseFactory.build(context = androidContext()) }
        single { get<AppDatabase>().userDao() }
    }
}