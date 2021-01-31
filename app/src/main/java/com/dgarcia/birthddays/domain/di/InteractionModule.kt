package com.dgarcia.birthddays.domain.di

import com.dgarcia.birthddays.domain.interaction.contact.GetContactsUseCase
import com.dgarcia.birthddays.domain.interaction.contact.GetContactsUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {
    factory<GetContactsUseCase> { GetContactsUseCaseImpl(get()) }
}