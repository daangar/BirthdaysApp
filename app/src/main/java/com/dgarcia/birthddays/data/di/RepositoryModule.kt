package com.dgarcia.birthddays.data.di

import com.dgarcia.birthddays.data.common.utils.Connectivity
import com.dgarcia.birthddays.data.common.utils.ConnectivityImpl
import com.dgarcia.birthddays.data.repository.ContactsRepositoryImpl
import com.dgarcia.birthddays.domain.repository.ContactsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<ContactsRepository> { ContactsRepositoryImpl(get(), get()) }
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
}