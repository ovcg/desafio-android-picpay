package com.picpay.desafio.android.di

import com.picpay.desafio.android.ui.user.UserViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object UserUIModule {

    val module = module {
        viewModel {
            UserViewModel(repository = get())
        }
    }
}