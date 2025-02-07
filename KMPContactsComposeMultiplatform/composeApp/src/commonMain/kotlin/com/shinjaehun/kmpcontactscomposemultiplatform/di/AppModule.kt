package com.shinjaehun.kmpcontactscomposemultiplatform.di

import com.shinjaehun.kmpcontactscomposemultiplatform.contacts.domain.ContactDataSource

expect class AppModule {
    val contactDataSource: ContactDataSource
}