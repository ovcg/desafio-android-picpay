package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.PicPayService
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDI {

    private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    val retrofitModule = module {
        singleInjections()
    }

    private fun Module.singleInjections() {
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
}