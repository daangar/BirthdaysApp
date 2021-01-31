package com.dgarcia.birthddays.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import com.dgarcia.birthddays.data.database.CONTACTS_TABLE_NAME
import com.dgarcia.birthddays.data.networking.base.DomainMapper
import com.dgarcia.birthddays.data.networking.model.ContactDobInfo
import com.dgarcia.birthddays.data.networking.model.ContactNameInfo
import com.dgarcia.birthddays.domain.model.Contact

@Entity(tableName = CONTACTS_TABLE_NAME, primaryKeys = ["name", "dob"])
data class ContactEntity (
    @Embedded
    val name: ContactNameInfo?,
    @Embedded
    val dob: ContactDobInfo?
) : DomainMapper<Contact> {

    override fun mapToDomainModel() = Contact(
        name?.first ?: "",
        name?.last ?: "",
        dob?.date ?: "",
        dob?.age ?: 0)

}