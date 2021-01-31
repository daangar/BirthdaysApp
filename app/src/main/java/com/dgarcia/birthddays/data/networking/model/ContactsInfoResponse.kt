package com.dgarcia.birthddays.data.networking.model

import com.dgarcia.birthddays.data.database.model.ContactEntity
import com.dgarcia.birthddays.data.networking.base.RoomMapper


data class ContactsInfoResponse(
    val contacts: List<ContactInfo>?
) : RoomMapper<List<ContactEntity>> {
    override fun mapToRoomEntity(): List<ContactEntity> {
        return contacts?.map {
            ContactEntity(it.name?.first ?: "", it.name?.last ?: "",
                it.dob?.date ?: "", it.dob?.age ?: 0)
        } ?: listOf()
    }
}

data class ContactInfo(
    val name: ContactNameInfo? = ContactNameInfo(),
    val dob: ContactDobInfo? = ContactDobInfo()
)

data class ContactNameInfo(
    val first: String? = "",
    val last: String? = ""
)

data class ContactDobInfo(
    val date: String? = "",
    val age: Int? = 0
)