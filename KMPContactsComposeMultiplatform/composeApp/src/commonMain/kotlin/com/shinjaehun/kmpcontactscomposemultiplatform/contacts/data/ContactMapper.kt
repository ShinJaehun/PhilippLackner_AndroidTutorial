package com.shinjaehun.kmpcontactscomposemultiplatform.contacts.data

import com.shinjaehun.kmpcontactscomposemultiplatform.contacts.domain.Contact
import com.shinjaehun.kmpcontactscomposemultiplatform.core.data.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.toContact(imageStorage: ImageStorage): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = imagePath?.let { imageStorage.getImage(it) }
    )
}