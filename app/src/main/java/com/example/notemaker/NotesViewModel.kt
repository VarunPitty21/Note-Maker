package com.example.notemaker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Notes>>
    private val repo : NotesRepository

    init {
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
         repo = NotesRepository(dao)
        allNotes = repo.allNotes
    }

    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        repo.delete(notes)
    }

    fun insertNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(notes)
    }
}