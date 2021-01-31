package com.dgarcia.birthddays.domain.interaction.contact

import com.dgarcia.birthddays.domain.model.Contact
import com.dgarcia.birthddays.domain.model.Result
import com.dgarcia.birthddays.domain.repository.ContactsRepository

class GetContactsUseCaseImpl(private val contactsRepository: ContactsRepository): GetContactsUseCase {
    override suspend fun invoke(param: Any?) = contactsRepository.getContacts()
}