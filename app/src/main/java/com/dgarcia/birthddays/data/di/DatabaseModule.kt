package com.dgarcia.birthddays.data.di

import androidx.room.Room
import com.dgarcia.birthddays.BuildConfig
import com.dgarcia.birthddays.data.database.ContactsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val CONTACTS_DB = "contacts-database"

val databaseModule = module {
    if (BuildConfig.DEBUG) {
        single {
            Room.databaseBuilder(androidContext(), ContactsDatabase::class.java, CONTACTS_DB)
                .fallbackToDestructiveMigration().build()
        }
    }

    factory { get<ContactsDatabase>().contactDao() }

}