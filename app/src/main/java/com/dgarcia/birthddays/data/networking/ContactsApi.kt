package com.dgarcia.birthddays.data.networking

import com.dgarcia.birthddays.data.networking.model.ContactsInfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ContactsApi {
    @GET("api/?results=20&seed=chalkboard&inc=name,dob")
    suspend fun getContacts(): Response<ContactsInfoResponse>
}