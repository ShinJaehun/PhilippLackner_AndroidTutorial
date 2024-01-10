package com.shinjaehun.spotifyclone.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.shinjaehun.spotifyclone.data.entities.Song
import com.shinjaehun.spotifyclone.other.Constants.SONG_COLLECTION
import kotlinx.coroutines.tasks.await

class MusicDatabase {
    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)


    suspend fun getAllSongs(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: java.lang.Exception) {
            emptyList()
        }
    }
}