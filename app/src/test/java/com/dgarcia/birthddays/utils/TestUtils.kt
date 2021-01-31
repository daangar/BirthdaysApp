package com.dgarcia.birthddays.utils

import com.dgarcia.birthddays.data.database.model.ContactEntity
import com.dgarcia.birthddays.data.networking.model.ContactDobInfo
import com.dgarcia.birthddays.data.networking.model.ContactNameInfo
import com.dgarcia.birthddays.data.networking.model.ContactsInfoResponse
import okhttp3.MediaType
import okhttp3.ResponseBody

const val FAKE_FAILURE_ERROR_CODE = 400
val succesContactsInfoResponse = ContactsInfoResponse(arrayListOf())
val failureResponseBody = ResponseBody.create(MediaType.parse("text"),"network error")
val fakeContactsEntities = listOf(ContactEntity(ContactNameInfo(), ContactDobInfo()))