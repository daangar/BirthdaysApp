package com.dgarcia.birthddays.domain.repository

import com.dgarcia.birthddays.domain.model.Contact
import com.dgarcia.birthddays.domain.model.Result

interface ContactsRepository {
    suspend fun getContacts(): Result<List<Contact>>
}