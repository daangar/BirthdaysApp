package com.dgarcia.birthddays.data.database.dao

import androidx.room.*
import com.dgarcia.birthddays.data.database.CONTACTS_TABLE_NAME
import com.dgarcia.birthddays.data.database.model.ContactEntity
import com.dgarcia.birthddays.domain.model.Contact

@Dao
interface ContactDao {

    @Transaction
    suspend fun updateContactsAndReturn(contacts: List<ContactEntity>): List<ContactEntity> {
        deleteContacts()
        saveContacts(contacts)
        return getContacts()
    }

    @Query("DELETE FROM $CONTACTS_TABLE_NAME")
    suspend fun deleteContacts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContacts(contacts: List<ContactEntity>)

    @Query("SELECT * FROM $CONTACTS_TABLE_NAME")
    suspend fun getContacts(): List<ContactEntity>
}