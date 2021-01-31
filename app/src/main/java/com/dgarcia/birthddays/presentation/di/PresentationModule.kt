package com.dgarcia.birthddays.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ContactsViewModel(get()) }
}