package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.source.local.UserLocalDataSource
import com.picpay.desafio.android.data.source.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.data.source.network.UserRemoteDataSource
import com.picpay.desafio.android.data.source.network.UserRemoteDataSourceImpl
import org.koin.dsl.module

object DataSourceDI {

    val dataSourceModule = module {
        single<UserLocalDataSource> { UserLocalDataSourceImpl() }
        single<UserRemoteDataSource> { UserRemoteDataSourceImpl(service = get()) }
    }
}