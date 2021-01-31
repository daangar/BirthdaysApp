package com.dgarcia.birthddays.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dgarcia.birthddays.data.database.CONTACTS_TABLE_NAME
import com.dgarcia.birthddays.data.networking.base.DomainMapper
import com.dgarcia.birthddays.data.networking.model.ContactDobInfo
import com.dgarcia.birthddays.data.networking.model.ContactNameInfo
import com.dgarcia.birthddays.domain.model.Contact

@Entity(tableName = CONTACTS_TABLE_NAME, primaryKeys = ["first", "last", "date", "age"])
data class ContactEntity (
    val first: String,
    val last: String,
    val date: String,
    val age: Int
) : DomainMapper<Contact> {

    override fun mapToDomainModel() = Contact(first, last, date, age)

}