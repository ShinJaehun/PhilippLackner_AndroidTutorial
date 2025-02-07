package com.shinjaehun.kmpcontactscomposemultiplatform.di

import android.content.Context
import com.shinjaehun.kmpcontactscomposemultiplatform.ContactDatabase
import com.shinjaehun.kmpcontactscomposemultiplatform.contacts.data.SqlDelightContactDataSource
import com.shinjaehun.kmpcontactscomposemultiplatform.contacts.domain.ContactDataSource
import com.shinjaehun.kmpcontactscomposemultiplatform.core.data.DatabaseDriverFactory
import com.shinjaehun.kmpcontactscomposemultiplatform.core.data.ImageStorage

actual class AppModule(
    private val context: Context
) {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory(context).create(),
            ),
            imageStorage = ImageStorage(context)
        )
    }
}