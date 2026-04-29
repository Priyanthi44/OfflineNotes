package com.myproject.offlinenotesapp.data.repository

import com.myproject.offlinenotesapp.data.model.Note
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NotesRepository @Inject constructor(){
    suspend fun getNotes():List<Note>{
        delay(3000)
        return listOf(Note(1, "Holiday", listOf("Book flights", "Pack bags")),
                Note(2, "Weekend", listOf("Shopping", "Sleeping in"))
        )
    }
}