package com.dgarcia.birthddays.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dgarcia.birthddays.data.database.dao.ContactDao
import com.dgarcia.birthddays.data.database.model.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class ContactsDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}