package com.shinjaehun.kmpcontactscomposemultiplatform.di

import com.shinjaehun.kmpcontactscomposemultiplatform.ContactDatabase
import com.shinjaehun.kmpcontactscomposemultiplatform.contacts.data.SqlDelightContactDataSource
import com.shinjaehun.kmpcontactscomposemultiplatform.contacts.domain.ContactDataSource
import com.shinjaehun.kmpcontactscomposemultiplatform.core.data.DatabaseDriverFactory
import com.shinjaehun.kmpcontactscomposemultiplatform.core.data.ImageStorage

actual class AppModule {
    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory().create()
            ),
            imageStorage = ImageStorage()
        )
    }
}