package com.dgarcia.birthddays

import com.dgarcia.birthddays.data.common.utils.Connectivity
import com.dgarcia.birthddays.data.database.dao.ContactDao
import com.dgarcia.birthddays.data.networking.ContactsApi
import com.dgarcia.birthddays.data.repository.ContactsRepositoryImpl
import com.dgarcia.birthddays.utils.fakeContactsEntities
import com.dgarcia.birthddays.utils.succesContactsInfoResponse
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class ContactsRepositoryTest {

    private val contactsTestApi: ContactsApi = mock()
    private val contactDao: ContactDao = mock()
    private val connectivity: Connectivity = mock()
    private val contactsRepository = ContactsRepositoryImpl(contactsTestApi, contactDao)

    @Test
    fun `test getContacts calls api and saves data to db upon sucess`() {
        runBlocking {
            whenever(connectivity.hasNetworkAccess()).thenReturn(true)
            whenever(contactsTestApi.getContacts()).thenReturn(Response.success(succesContactsInfoResponse))
            whenever(contactDao.updateContactsAndReturn(succesContactsInfoResponse.mapToRoomEntity())).thenReturn(
                fakeContactsEntities)

            contactsRepository.getContacts()

            verify(contactsTestApi, times(1)).getContacts()
            verify(contactDao, times(1)).updateContactsAndReturn(fakeContactsEntities)
        }
    }

}