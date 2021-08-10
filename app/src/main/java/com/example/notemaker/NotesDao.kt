package com.example.notemaker

import androidx.room.*

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(notes: Notes)

    @Delete
    fun delete(notes: Notes)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(notes: Notes)
}