package com.example.notemaker

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao : NotesDao) {

    val allNotes : LiveData<List<Notes>> = noteDao.getAllNotes()

    suspend fun insert(notes: Notes){
        noteDao.insert(notes)
    }

    suspend fun delete(notes: Notes){
        noteDao.delete(notes)
    }

}