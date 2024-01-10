package com.shinjaehun.roomexample

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("select * from contact order by firstName asc")
    fun getContactsOrderedByFirstName() : Flow<List<Contact>>

    @Query("select * from contact order by lastName asc")
    fun getContactsOrderedByLastName() : Flow<List<Contact>>

    @Query("select * from contact order by phoneNumber asc")
    fun getContactsOrderedByPhoneNumber() : Flow<List<Contact>>

}