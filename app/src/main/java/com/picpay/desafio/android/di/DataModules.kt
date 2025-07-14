package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.data.UserRepository
import com.picpay.desafio.android.data.UserRepositoryImpl
import com.picpay.desafio.android.data.source.local.UserLocalDataSource
import com.picpay.desafio.android.data.source.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.data.source.network.UserRemoteDataSource
import com.picpay.desafio.android.data.source.network.UserRemoteDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModules {

    private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    val dataModules = module {
        retrofit()
        dataSourceModule()
        repository()
    }

    private fun Module.retrofit() {
        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        single {
            get<Retrofit>().create(PicPayService::class.java)
        }
    }

    private fun Module.dataSourceModule() {
        single<UserLocalDataSource> { UserLocalDataSourceImpl() }
        single<UserRemoteDataSource> { UserRemoteDataSourceImpl(service = get()) }
    }

    private fun Module.repository() {
        single<UserRepository> {
            UserRepositoryImpl(
                localDataSource = get(),
                remoteDataSource = get(),
                isNetworkAvailable = get()
            )
        }
    }
}