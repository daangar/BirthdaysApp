package com.dgarcia.birthddays.presentation.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.dgarcia.birthddays.data.common.coroutine.CoroutineContextProvider
import org.koin.dsl.module

val appModule = module {
    single { CoroutineContextProvider() }
    single { (activity: AppCompatActivity) -> AppNavigator(activity) }
    single { (activity: FragmentActivity) -> AppFragmentNavigator(activity) }
}