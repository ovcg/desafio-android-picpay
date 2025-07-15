package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.common.database.AppDatabase
import com.picpay.desafio.android.data.common.database.AppDatabaseFactory
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.data.source.local.UserLocalDataSource
import com.picpay.desafio.android.data.source.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.data.source.network.UserRemoteDataSource
import com.picpay.desafio.android.data.source.network.UserRemoteDataSourceImpl
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import com.picpay.desafio.android.util.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Modules {

    private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    val module = module {
        retrofit()
        database()
        dataSourceModule()
        repository()
        viewModel()
        utils()
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

    private fun Module.database() {
        single { AppDatabaseFactory.build(context = androidContext()) }
        single { get<AppDatabase>().userDao() }
    }

    private fun Module.dataSourceModule() {
        single<UserLocalDataSource> { UserLocalDataSourceImpl(userDao = get()) }
        single<UserRemoteDataSource> { UserRemoteDataSourceImpl(service = get()) }
    }

    private fun Module.repository() {
        single<UserRepository> {
            UserRepositoryImpl(
                localDataSource = get(),
                remoteDataSource = get(),
                networkHelper = get()
            )
        }
    }

    private fun Module.viewModel() {
        viewModel {
            UserViewModel(repository = get())
        }
    }

    private fun Module.utils() {
        factory { NetworkHelper(context = androidContext()) }
    }
}