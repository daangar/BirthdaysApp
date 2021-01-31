package com.dgarcia.birthddays.data.repository

import com.dgarcia.birthddays.data.database.dao.ContactDao
import com.dgarcia.birthddays.data.database.model.ContactEntity
import com.dgarcia.birthddays.data.networking.ContactsApi
import com.dgarcia.birthddays.domain.model.Contact
import com.dgarcia.birthddays.domain.model.Result
import com.dgarcia.birthddays.domain.repository.ContactsRepository
import com.dgarcia.birthddays.data.networking.base.getData

class ContactsRepositoryImpl(
    private val contactsApi: ContactsApi,
    private val contactDao: ContactDao
): BaseRepository<Contact, ContactEntity>(), ContactsRepository {
    override suspend fun getContacts(): Result<List<Contact>> {
        return fetchListData(
            apiDataProvider = {
                contactsApi.getContacts().getData(
                    fetchFromCacheAction = {contactDao.getContacts()},
                    cacheAction = { contactDao.saveContacts(it)}
                )
            },
            dbDataProvider = {contactDao.getContacts()}
        )
    }
}